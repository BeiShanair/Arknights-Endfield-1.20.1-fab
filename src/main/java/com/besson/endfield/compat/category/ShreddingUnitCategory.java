package com.besson.endfield.compat.category;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.compat.display.ShreddingUnitDisplay;
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

public class ShreddingUnitCategory implements DisplayCategory<ShreddingUnitDisplay> {
    public static final CategoryIdentifier<ShreddingUnitDisplay> SHREDDING_UNIT =
            CategoryIdentifier.of(ArknightsEndfield.MOD_ID, "shredding_unit");

    @Override
    public CategoryIdentifier<? extends ShreddingUnitDisplay> getCategoryIdentifier() {
        return SHREDDING_UNIT;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("blockEntity.shredding_unit");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.SHREDDING_UNIT.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(ShreddingUnitDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));

        Point start = new Point(bounds.getCenterX() - 60, bounds.getCenterY() - 8);
        widgets.add(Widgets.createSlot(new Point(start.x, start.y))
                .entries(display.getInputEntries().get(0))
                .markInput());

        widgets.add(Widgets.createArrow(new Point(start.x + 50, start.y + 1)));
        widgets.add(Widgets.createSlot(new Point(start.x + 100, start.y))
                .entries(display.getOutputEntries().get(0))
                .markOutput());
        return widgets;
    }
}
