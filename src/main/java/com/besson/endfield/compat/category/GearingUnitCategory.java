package com.besson.endfield.compat.category;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.compat.display.GearingUnitDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GearingUnitCategory implements DisplayCategory<GearingUnitDisplay> {
    public static final CategoryIdentifier<GearingUnitDisplay> GEARING_UNIT =
            CategoryIdentifier.of(ArknightsEndfield.MOD_ID, "gearing_unit");
    @Override
    public CategoryIdentifier<? extends GearingUnitDisplay> getCategoryIdentifier() {
        return GEARING_UNIT;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("blockEntity.gearing_unit");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.GEARING_UNIT.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(GearingUnitDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();

        Point start = new Point(bounds.getCenterX() - 60, bounds.getCenterY() - 8);
        widgets.add(Widgets.createRecipeBase(bounds));

        List<EntryIngredient> inputs = display.getInputEntries();
        for (int i = 0; i < inputs.size(); i++) {
            widgets.add(Widgets.createSlot(new Point(start.x + i * 20, start.y))
                    .entries(inputs.get(i))
                    .markInput());
        }

        widgets.add(Widgets.createArrow(new Point(start.x + 50, start.y + 1)));
        widgets.add(Widgets.createSlot(new Point(start.x + 100, start.y))
                .entries(display.getOutputEntries().get(0))
                .markOutput());
        return widgets;
    }
}
