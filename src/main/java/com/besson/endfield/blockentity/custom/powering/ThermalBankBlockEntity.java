package com.besson.endfield.blockentity.custom.powering;

import com.besson.endfield.block.custom.powering.ThermalBankBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.item.ModItems;
import com.besson.endfield.utils.PowerNetworkManager;
import com.besson.endfield.screen.custom.ThermalBankScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ThermalBankBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final SimpleInventory inputInv;
    private final InventoryStorage inputStorage;
    private boolean registeredToManager = false;
    protected boolean isWorking;
    protected boolean enable = true;
    protected boolean needsInit = true;
    private int burnTime;
    private int fuelTime;
    protected final PropertyDelegate propertyDelegate;
    private Item burnItem = null;
            
    public ThermalBankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.THERMAL_BANK, pos, state);
        this.inputInv = new SimpleInventory(1) {
            @Override
            public void markDirty() {
                super.markDirty();
                ThermalBankBlockEntity.this.markDirty();
            }
        };
        this.inputStorage = createInputStorage();
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ThermalBankBlockEntity.this.burnTime;
                    case 1 -> ThermalBankBlockEntity.this.fuelTime;
                    case 2 -> ThermalBankBlockEntity.this.enable ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ThermalBankBlockEntity.this.burnTime = value;
                    case 1 -> ThermalBankBlockEntity.this.fuelTime = value;
                    case 2 -> ThermalBankBlockEntity.this.enable = value == 1;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    public SimpleInventory getInputInv() {
        return inputInv;
    }
    
    protected InventoryStorage createInputStorage() {
        return InventoryStorage.of(inputInv, null);
    }
    
    @Override
    public void setWorld(World world) {
        super.setWorld(world);
        if (world instanceof ServerWorld) {
            needsInit = true;
        }
    }

    @Override
    public void markRemoved() {
        if (world instanceof ServerWorld serverWorld) {
            PowerNetworkManager.get(serverWorld).unregisterGenerator(this.getPos());
            registeredToManager = false;
        }
        super.markRemoved();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    
    public DefaultedList<ItemStack> getItems() {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(1, ItemStack.EMPTY);
        if (inputInv != null) {
            list.set(0, inputInv.getStack(0));
        }
        return list;
    }

    public static void tick(World world, BlockPos pos, BlockState state, ThermalBankBlockEntity entity) {
        if (world.isClient()) return;

        if (entity.needsInit && world instanceof ServerWorld serverWorld) {
            entity.needsInit = false;

            PowerNetworkManager.get(serverWorld).registerGenerator(entity.getPos(), entity::getPowerOutput);
            entity.registeredToManager = true;
        }

        if (!entity.getEnable()) {
            entity.isWorking = false;
            world.updateListeners(pos, state, state, 3);
            entity.markDirty();
            return;
        }
        
        if (entity.burnTime > 0) {
            entity.burnTime--;
        }

        if (entity.burnTime == 0 && !entity.inputInv.getStack(0).isEmpty()) {
            entity.burnItem = null;
            ItemStack stack = entity.inputInv.getStack(0);
            Integer fuelValue;
            if (stack.isOf(ModItems.ORIGINIUM_ORE)) {
                fuelValue = 160;
            } else if (stack.isOf(ModItems.LC_BATTERY) ||
                    stack.isOf(ModItems.SC_BATTERY) ||
                    stack.isOf(ModItems.HC_BATTERY)) {
                fuelValue = 800;
            } else {
                fuelValue = FuelRegistry.INSTANCE.get(stack.getItem());
            }
            entity.burnItem = stack.getItem();
            if (fuelValue != null && fuelValue > 0) {
                int fuelTime = fuelValue / 2;
                entity.fuelTime = fuelTime;
                entity.burnTime = fuelTime;
                if (stack.isOf(Items.LAVA_BUCKET)) {
                    entity.inputInv.setStack(0, new ItemStack(Items.BUCKET));
                } else {
                    entity.inputInv.getStack(0).decrement(1);
                }
                entity.markDirty();
            }
        }
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public int getPowerOutput() {
        if (!enable || !isBurning()) return 0;
        if (burnItem == ModItems.ORIGINIUM_ORE) {
            return 50;
        } else if (burnItem == ModItems.LC_BATTERY) {
            return 220;
        } else if (burnItem == ModItems.SC_BATTERY) {
            return 420;
        } else if (burnItem == ModItems.HC_BATTERY) {
            return 1100;
        }
        return 50;
    }

    public float getFuelProgress() {
        if (fuelTime == 0) return 0;
        return (float) burnTime / (float) fuelTime;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtCompound inputTag = new NbtCompound();
        Inventories.writeNbt(inputTag, inputInv.stacks);
        nbt.put("input", inputTag);
        nbt.putInt("burnTime", burnTime);
        nbt.putInt("fuelTime", fuelTime);
        nbt.putBoolean("isWorking", this.isWorking);
        nbt.putBoolean("enable", this.enable);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("input")) {
            Inventories.readNbt(nbt.getCompound("input"), inputInv.stacks);
        }
        burnTime = nbt.getInt("burnTime");
        fuelTime = nbt.getInt("fuelTime");
        this.isWorking = nbt.getBoolean("isWorking");
        this.enable = nbt.getBoolean("enable");
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.thermal_bank");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ThermalBankScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Nullable
    public Storage<ItemVariant> getStorage(BlockState state, Direction side) {
        Direction facing = getFacing(state);

        if (side == facing) {
            return inputStorage;
        }
        return null;
    }
    
    protected Direction getFacing(BlockState state) {
        return state.get(ThermalBankBlock.FACING);
    }
    
    public void setEnable(boolean enable) {
        this.enable = enable;
        markDirty();
        if (world != null) {
            world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        }
    }

    public boolean getEnable() {
        return this.enable;
    }
}
