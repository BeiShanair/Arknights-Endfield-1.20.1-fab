package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.ElectrifiableDevice;
import com.besson.endfield.blockentity.ImplementedInventory;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.recipe.custom.OreRigRecipe;
import com.besson.endfield.screen.custom.ElectricMiningRigScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class ElectricMiningRigBlockEntity extends BlockEntity implements SidedInventory, GeoBlockEntity, ExtendedScreenHandlerFactory, ImplementedInventory, ElectrifiableDevice {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private static final int OUTPUT_SLOT = 0;

    private int storePower = 0;
    private static final int POWER_PRE_TICK = 5;
    private boolean isWorking = false;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 40;

    public ElectricMiningRigBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ELECTRIC_MINING_RIG, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ElectricMiningRigBlockEntity.this.progress;
                    case 1 -> ElectricMiningRigBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ElectricMiningRigBlockEntity.this.progress = value;
                    case 1 -> ElectricMiningRigBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    public static void tick(World world, BlockPos pos, BlockState state, ElectricMiningRigBlockEntity entity) {
        if (world.isClient()) return;

        if (entity.isOutputSlotAvailable()) {
             boolean hasRecipe = entity.hasCorrectRecipe(world);

            if (entity.needsPower() || !hasRecipe) {
                entity.isWorking = false;
            } else if (!entity.needsPower() && !entity.isWorking) {
                entity.isWorking = true;
            }

            entity.markDirty();
            world.updateListeners(pos, state, state, 3);

            if (hasRecipe && entity.storePower >= POWER_PRE_TICK) {
                entity.increaseProgress();
                entity.storePower -= POWER_PRE_TICK;
                if (entity.hasCraftFinished()) {
                    entity.craftItem(world);
                    entity.resetProgress();
                }
            } else {
                entity.resetProgress();
            }
        } else {
            entity.resetProgress();
        }
        entity.markDirty();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem(World world) {
        Optional<OreRigRecipe> match = getMatchRecipe(world);

        if (match.isPresent()) {
            ItemStack result = match.get().getOutput(world.getRegistryManager());
            this.setStack(OUTPUT_SLOT,
                    new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
        }
    }

    private Optional<OreRigRecipe> getMatchRecipe(World world) {
        SimpleInventory inv = new SimpleInventory(1);
        BlockState below = world.getBlockState(this.pos.down());
        ItemStack belowStack = below.getBlock().asItem().getDefaultStack();
        inv.setStack(0, belowStack);

        return world.getRecipeManager()
                .getFirstMatch(OreRigRecipe.Type.INSTANCE, inv, world)
                .map(recipe -> (OreRigRecipe) recipe);
     }

     private boolean hasCraftFinished() {
        return this.progress >= this.maxProgress;
     }

     private void increaseProgress() {
        this.progress++;
     }

     private boolean hasCorrectRecipe(World world) {
        Optional<OreRigRecipe> match = getMatchRecipe(world);

        if (match.isPresent()) {
            ItemStack result = match.get().getOutput(world.getRegistryManager());
            return canInsertAmountIntoOutput(result) && canInsertItemIntoOutputSlot(result.getItem());
        }
        return false;
     }

     private boolean canInsertAmountIntoOutput(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= this.getMaxCountPerStack();
     }

     private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
     }

     private boolean isOutputSlotAvailable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
     }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0,
                state -> this.isWorking
                        ? state.setAndContinue(RawAnimation.begin().thenLoop("working"))
                        : state.setAndContinue(RawAnimation.begin().thenLoop("idle"))));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("progress", progress);
        nbt.putBoolean("isWorking", isWorking);
        nbt.putInt("storePower", storePower);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        this.progress = nbt.getInt("progress");
        this.isWorking = nbt.getBoolean("isWorking");
        this.storePower = nbt.getInt("storePower");
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public void receiveElectricCharge(int amount) {
        this.storePower += amount;
        if (this.storePower > 100) {
            this.storePower = 100;
        }
    }

    @Override
    public boolean needsPower() {
        return this.storePower < POWER_PRE_TICK;
    }

    @Override
    public int getRequiredPower() {
        return POWER_PRE_TICK;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.electric_mining_rig");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ElectricMiningRigScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[]{OUTPUT_SLOT};
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot == OUTPUT_SLOT;
    }
}
