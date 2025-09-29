package com.besson.endfield;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.item.ModItemGroups;
import com.besson.endfield.item.ModItems;
import com.besson.endfield.recipe.ModRecipes;
import com.besson.endfield.screen.ModScreens;
import com.besson.endfield.utils.JoinHandler;
import com.besson.endfield.world.generation.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class ArknightsEndfield implements ModInitializer {

	public static final String MOD_ID = "arknights-endfield";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroup();

		ModBlockEntities.registerModBlockEntities();
		ModScreens.register();
		ModRecipes.registerModRecipes();

		ModWorldGeneration.registerWorldGeneration();

		GeckoLib.initialize();
		JoinHandler.register();

		LOGGER.info("Hello Fabric world!");
	}
}