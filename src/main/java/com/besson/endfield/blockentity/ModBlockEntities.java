package com.besson.endfield.blockentity;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.custom.*;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModBlockEntities {

    public static final BlockEntityType<ProtocolAnchorCoreBlockEntity> PROTOCOL_ANCHOR_CORE = create("protocol_anchor_core",
            BlockEntityType.Builder.create(ProtocolAnchorCoreBlockEntity::new, ModBlocks.PROTOCOL_ANCHOR_CORE));

    public static final BlockEntityType<RelayTowerBlockEntity> RELAY_TOWER = create("relay_tower",
            BlockEntityType.Builder.create(RelayTowerBlockEntity::new, ModBlocks.RELAY_TOWER));

    public static final BlockEntityType<ElectricPylonBlockEntity> ELECTRIC_PYLON = create("electric_pylon",
            BlockEntityType.Builder.create(ElectricPylonBlockEntity::new, ModBlocks.ELECTRIC_PYLON));

    public static final BlockEntityType<ElectricMiningRigBlockEntity> ELECTRIC_MINING_RIG = create("electric_mining_rig",
            BlockEntityType.Builder.create(ElectricMiningRigBlockEntity::new, ModBlocks.ELECTRIC_MINING_RIG));

    public static final BlockEntityType<ElectricMiningRigMkIIBlockEntity> ELECTRIC_MINING_RIG_MK_II = create("electric_mining_rig_mk_ii",
            BlockEntityType.Builder.create(ElectricMiningRigMkIIBlockEntity::new, ModBlocks.ELECTRIC_MINING_RIG_MK_II));

    public static final BlockEntityType<PortableOriginiumRigBlockEntity> PORTABLE_ORIGINIUM_RIG = create("portable_originium_rig",
            BlockEntityType.Builder.create(PortableOriginiumRigBlockEntity::new, ModBlocks.PORTABLE_ORIGINIUM_RIG));

    public static final BlockEntityType<RefiningUnitBlockEntity> REFINING_UNIT = create("refining_unit",
            BlockEntityType.Builder.create(RefiningUnitBlockEntity::new, ModBlocks.REFINING_UNIT));

    public static final BlockEntityType<ShreddingUnitBlockEntity> SHREDDING_UNIT = create("shredding_unit",
            BlockEntityType.Builder.create(ShreddingUnitBlockEntity::new, ModBlocks.SHREDDING_UNIT));

    public static final BlockEntityType<FillingUnitBlockEntity> FILLING_UNIT = create("filling_unit",
            BlockEntityType.Builder.create(FillingUnitBlockEntity::new, ModBlocks.FILLING_UNIT));

    public static final BlockEntityType<FittingUnitBlockEntity> FITTING_UNIT = create("fitting_unit",
            BlockEntityType.Builder.create(FittingUnitBlockEntity::new, ModBlocks.FITTING_UNIT));

    public static final BlockEntityType<GearingUnitBlockEntity> GEARING_UNIT = create("gearing_unit",
            BlockEntityType.Builder.create(GearingUnitBlockEntity::new, ModBlocks.GEARING_UNIT));

    public static final BlockEntityType<GrindingUnitBlockEntity> GRINDING_UNIT = create("grinding_unit",
            BlockEntityType.Builder.create(GrindingUnitBlockEntity::new, ModBlocks.GRINDING_UNIT));

    public static final BlockEntityType<MouldingUnitBlockEntity> MOULDING_UNIT = create("moulding_unit",
            BlockEntityType.Builder.create(MouldingUnitBlockEntity::new, ModBlocks.MOULDING_UNIT));

    public static final BlockEntityType<PackagingUnitBlockEntity> PACKAGING_UNIT = create("packaging_unit",
            BlockEntityType.Builder.create(PackagingUnitBlockEntity::new, ModBlocks.PACKAGING_UNIT));

    public static final BlockEntityType<PlantingUnitBlockEntity> PLANTING_UNIT = create("planting_unit",
            BlockEntityType.Builder.create(PlantingUnitBlockEntity::new, ModBlocks.PLANTING_UNIT));

    public static final BlockEntityType<SeedPickingUnitBlockEntity> SEED_PICKING_UNIT = create("seed_picking_unit",
            BlockEntityType.Builder.create(SeedPickingUnitBlockEntity::new, ModBlocks.SEED_PICKING_UNIT));

    public static final BlockEntityType<ThermalBankBlockEntity> THERMAL_BANK = create("thermal_bank",
            BlockEntityType.Builder.create(ThermalBankBlockEntity::new, ModBlocks.THERMAL_BANK));

    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(ArknightsEndfield.MOD_ID, id), builder.build(type));
    }

    public static void registerModBlockEntities() {

    }
}
