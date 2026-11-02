package com.besson.endfield.compat.category;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.compat.display.ForgeOfTheSkyDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ForgeOfTheSkyCategory implements DisplayCategory<ForgeOfTheSkyDisplay> {
    public static final CategoryIdentifier<ForgeOfTheSkyDisplay> FORGE_OF_THE_SKY =
            CategoryIdentifier.of(ArknightsEndfield.MOD_ID, "forge_of_the_sky");

    @Override
    public CategoryIdentifier<? extends ForgeOfTheSkyDisplay> getCategoryIdentifier() {
        return FORGE_OF_THE_SKY;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("blockEntity.forge_of_the_sky");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.FORGE_OF_THE_SKY.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(ForgeOfTheSkyDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));

        Point start = new Point(bounds.getCenterX() - 60, bounds.getCenterY() - 8);

        widgets.add(Widgets.createSlot(new Point(start.x, start.y))
                .entries(display.getInputEntries().get(0))
                .markInput());
        if (display.getInputEntries().size() >= 2) {
            widgets.add(Widgets.createSlot(new Point(start.x + 22, start.y))
                    .entries(display.getInputEntries().get(1))
                    .markInput());
        }
        widgets.add(Widgets.createArrow(new Point(start.x + 45, start.y + 1)));
        widgets.add(Widgets.createSlot(new Point(start.x + 78, start.y))
                .entries(display.getOutputEntries().get(0))
                .markOutput());
        return widgets;
    }
}
