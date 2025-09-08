package com.besson.endfield.datagen;

import com.besson.endfield.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTablesProvider extends FabricBlockLootTableProvider {
    public ModLootTablesProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PROTOCOL_ANCHOR_CORE);
        addDrop(ModBlocks.RELAY_TOWER);
        addDrop(ModBlocks.ELECTRIC_PYLON);
        addDrop(ModBlocks.ELECTRIC_MINING_RIG);
        addDrop(ModBlocks.ELECTRIC_MINING_RIG_MK_II);
        addDrop(ModBlocks.PORTABLE_ORIGINIUM_RIG);
        addDrop(ModBlocks.REFINING_UNIT);
        addDrop(ModBlocks.SHREDDING_UNIT);
    }
}
