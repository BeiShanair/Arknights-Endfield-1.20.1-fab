package com.besson.endfield.datagen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.ModItems;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.data.DataOutput;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.BiConsumer;

public class ModWelcomePackagesProvider extends FabricCodecDataProvider<ModWelcomePackagesProvider.WelcomePackage> {
	public record WelcomePackage(int version, List<ItemStack> items) {
		public static final Codec<WelcomePackage> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				Codec.INT.fieldOf("version").forGetter(WelcomePackage::version),
				ItemStack.CODEC.listOf().fieldOf("items").forGetter(WelcomePackage::items)
		).apply(instance, WelcomePackage::new));
	}

	public ModWelcomePackagesProvider(FabricDataOutput output) {
		super(output, DataOutput.OutputType.DATA_PACK, "welcome_packages", WelcomePackage.CODEC);
	}

	@Override
	public String getName() {
		return "Welcome Packages";
	}

	@Override
	protected void configure(BiConsumer<Identifier, WelcomePackage> provider) {
		provider.accept(id("v1"), new WelcomePackage(1, List.of(
				guideBook(),
				stack(ModItems.PROTOCOL_ANCHOR_CORE_ITEM, 1),
				stack(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK, 10),
				stack(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK, 10),
				stack(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK, 10),
				stack(ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK, 10)
		)));
	}

	private static Identifier id(String path) {
		return new Identifier(ArknightsEndfield.MOD_ID, path);
	}

	private static ItemStack stack(Item item, int count) {
		return new ItemStack(item, count);
	}

	private static ItemStack stack(net.minecraft.block.Block block, int count) {
		return new ItemStack(block.asItem(), count);
	}
	
	private static ItemStack guideBook() {
		Item item = Registries.ITEM.get(new Identifier("patchouli", "guide_book"));
		if (item == Items.AIR) {
			throw new IllegalStateException("找不到 patchouli:guide_book —— 数据生成环境里 Patchouli 没加载？"
					+ "检查 build.gradle 的 modImplementation \"vazkii.patchouli:Patchouli\"");
		}
		ItemStack stack = new ItemStack(item);
		NbtCompound nbt = stack.getOrCreateNbt();
		nbt.putString("patchouli:book", id("endfield_book").toString());
		return stack;
	}
}
