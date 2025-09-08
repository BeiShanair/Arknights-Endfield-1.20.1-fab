package com.besson.endfield.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup ARKNIGHTS_ENDFIELD = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ArknightsEndfield.MOD_ID, "arknights_endfield"),
            ItemGroup.create(null, -1)
                    .displayName(Text.translatable("itemGroup.arknights_endfield"))
                    .icon(() -> new ItemStack(ModBlocks.PROTOCOL_ANCHOR_CORE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PROTOCOL_ANCHOR_CORE_ITEM);
                        entries.add(ModItems.RELAY_TOWER_ITEM);
                        entries.add(ModItems.ELECTRIC_PYLON_ITEM);
                        entries.add(ModItems.ELECTRIC_MINING_RIG_ITEM);
                        entries.add(ModItems.ELECTRIC_MINING_RIG_MK_II_ITEM);
                        entries.add(ModItems.PORTABLE_ORIGINIUM_RIG_ITEM);
                        entries.add(ModItems.REFINING_UNIT_ITEM);
                        entries.add(ModItems.SHREDDING_UNIT_ITEM);
                    }).build());

    public static void registerItemGroup(){

    }
}
