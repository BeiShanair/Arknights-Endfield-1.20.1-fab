package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.recipe.custom.CrafterRecipe;
import com.besson.endfield.screen.ModScreens;
import com.besson.endfield.utils.CrafterResultSlot;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CrafterScreenHandler extends ScreenHandler {
    private static final int INPUT_SLOT_COUNT = 3;
    private static final int OUTPUT_SLOT_INDEX = 3;
    private static final int PLAYER_INVENTORY_START = 4;
    private static final int HOTBAR_END = 40;

    private final Inventory inventory;
    private final Inventory outputInventory;
    private final PlayerEntity player;
    private final Slot outputSlot;
    private final Property selectedRecipe = Property.create();
    private Runnable contentsChangedListener = () -> {};

    private List<CrafterRecipe> currentRecipes = new ArrayList<>();

    public CrafterScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(packetByteBuf.readBlockPos()));
    }

    public CrafterScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreens.CRAFTER_SCREEN, syncId);
        checkSize((Inventory) blockEntity, INPUT_SLOT_COUNT);
        this.inventory = (Inventory) blockEntity;
        inventory.onOpen(playerInventory.player);
        this.player = playerInventory.player;
        this.outputInventory = new SimpleInventory(1);

        this.addSlot(new Slot(inventory, 0, 26, 17) {
            @Override
            public void markDirty() {
                super.markDirty();
                CrafterScreenHandler.this.onContentChanged(this.inventory);
            }
        });
        this.addSlot(new Slot(inventory, 1, 26, 35) {
            @Override
            public void markDirty() {
                super.markDirty();
                CrafterScreenHandler.this.onContentChanged(this.inventory);
            }
        });
        this.addSlot(new Slot(inventory, 2, 26, 53) {
            @Override
            public void markDirty() {
                super.markDirty();
                CrafterScreenHandler.this.onContentChanged(this.inventory);
            }
        });
        this.outputSlot = this.addSlot(new CrafterResultSlot(player, inventory, outputInventory, 0, 124, 35, this));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        this.addProperty(this.selectedRecipe);
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
            Item item = originalStack.getItem();
            newStack = originalStack.copy();

            if (invSlot == OUTPUT_SLOT_INDEX) {
                item.onCraft(originalStack, player.getWorld(), player);
                if (!this.insertItem(originalStack, PLAYER_INVENTORY_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(originalStack, newStack);
            } else if (invSlot >= PLAYER_INVENTORY_START && invSlot < HOTBAR_END) {
                if (!this.insertItem(originalStack, 0, INPUT_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, PLAYER_INVENTORY_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            }

            slot.markDirty();
            if (originalStack.getCount() == newStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, originalStack);
            this.sendContentUpdates();
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.outputInventory && super.canInsertIntoSlot(stack, slot);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.inventory) {
            this.updateRecipes(true);
            this.contentsChangedListener.run();
        }
    }

    private void updateRecipes(boolean resetSelection) {
        World world = player.getWorld();

        currentRecipes = world.getRecipeManager()
                .getAllMatches(CrafterRecipe.Type.INSTANCE, inventory, world);

        if (resetSelection) {
            selectedRecipe.set(-1);
        } else if (!isSelectedRecipeInBounds()) {
            selectedRecipe.set(-1);
        }

        populateResult();
    }

    public void updateResult() {
        this.updateRecipes(false);
    }

    private void populateResult() {
        if (isSelectedRecipeInBounds()) {
            ItemStack result = currentRecipes.get(selectedRecipe.get()).craft(inventory, player.getWorld().getRegistryManager());
            outputSlot.setStackNoCallbacks(result);
        } else {
            outputSlot.setStackNoCallbacks(ItemStack.EMPTY);
        }

        outputInventory.markDirty();
        this.sendContentUpdates();
    }

    public void changeRecipe() {
        if (currentRecipes.isEmpty()) {
            return;
        }

        int next = isSelectedRecipeInBounds() ? (selectedRecipe.get() + 1) % currentRecipes.size() : 0;
        selectRecipe(next);
    }

    public boolean selectRecipe(int index) {
        if (!isRecipeInBounds(index)) {
            return false;
        }

        selectedRecipe.set(index);
        populateResult();
        return true;
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        return selectRecipe(id);
    }

    private boolean isRecipeInBounds(int index) {
        return index >= 0 && index < currentRecipes.size();
    }

    private boolean isSelectedRecipeInBounds() {
        return isRecipeInBounds(selectedRecipe.get());
    }

    public List<CrafterRecipe> getCurrentRecipes() {
        return currentRecipes;
    }

    public int getAvailableRecipeCount() {
        return currentRecipes.size();
    }

    public boolean canCraft() {
        return !currentRecipes.isEmpty();
    }

    public int getSelectedRecipeIndex() {
        return selectedRecipe.get();
    }

    public void setContentsChangedListener(Runnable contentsChangedListener) {
        this.contentsChangedListener = contentsChangedListener;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        if (!player.getWorld().isClient()) {
            this.outputInventory.removeStack(0);
            this.dropInventory(player, this.inventory);
        }
    }
}
