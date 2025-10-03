package com.besson.endfield.screen.custom;

import com.besson.endfield.blockentity.custom.CrafterBlockEntity;
import com.besson.endfield.recipe.custom.CrafterRecipe;
import com.besson.endfield.screen.ModScreens;
import com.besson.endfield.utils.CrafterResultSlot;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

import java.util.List;

public class CrafterScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final Inventory outputInventory;
    private final CrafterBlockEntity entity;
    private final PlayerEntity player;

    private List<CrafterRecipe> currentRecipes = List.of();
    private int selectedRecipeIndex = 0;

    public CrafterScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(packetByteBuf.readBlockPos()));
    }

    public CrafterScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreens.CRAFTER_SCREEN, syncId);
        checkSize((Inventory) blockEntity, 4);
        this.inventory = (Inventory) blockEntity;
        inventory.onOpen(playerInventory.player);
        this.entity = (CrafterBlockEntity) blockEntity;
        this.player = playerInventory.player;
        this.outputInventory = new SimpleInventory(1);

        this.addSlot(new Slot(inventory, 0, 30, 35) {
            @Override
            public void markDirty() {
                super.markDirty();
                CrafterScreenHandler.this.onContentChanged(this.inventory);
            }
        });
        this.addSlot(new Slot(inventory, 1, 48, 35) {
            @Override
            public void markDirty() {
                super.markDirty();
                CrafterScreenHandler.this.onContentChanged(this.inventory);
            }
        });
        this.addSlot(new Slot(inventory, 2, 66, 35) {
            @Override
            public void markDirty() {
                super.markDirty();
                CrafterScreenHandler.this.onContentChanged(this.inventory);
            }
        });
        this.addSlot(new CrafterResultSlot(player, inventory, outputInventory, 0, 124, 35, this));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (invSlot == 3) {
                while (true) {
                    slot.onTakeItem(player, originalStack);
                    this.updateResult();

                    if (!slot.hasStack() || !this.canInsertIntoSlot(originalStack, this.slots.get(3))) {
                        break;
                    }

                    ItemStack crafted = slot.getStack();
                    if (!this.insertItem(crafted, 4, 40, true)) {
                        break;
                    }
                }
            } else {
                if (!this.insertItem(originalStack, 0, 3, false)) {
                    return ItemStack.EMPTY;
                }
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.inventory) {
            this.updateResult();
        }
    }

    public void updateResult() {
        World world = player.getWorld();
        if (world.isClient()) return;

        currentRecipes = world.getRecipeManager()
                .getAllMatches(CrafterRecipe.Type.INSTANCE, inventory, world);
        if (!currentRecipes.isEmpty()) {
            if (selectedRecipeIndex >= currentRecipes.size()) {
                selectedRecipeIndex = 0;
            }
            ItemStack result = currentRecipes.get(selectedRecipeIndex).getOutput(world.getRegistryManager());
            outputInventory.setStack(0, result.copy());
        } else {
            outputInventory.setStack(0, ItemStack.EMPTY);
        }
        outputInventory.markDirty();
    }

    public void changeRecipe() {
        if (!currentRecipes.isEmpty()) {
            selectedRecipeIndex = (selectedRecipeIndex + 1) % currentRecipes.size();
            updateResult();
        }
    }

}
