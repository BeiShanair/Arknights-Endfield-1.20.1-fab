package com.besson.endfield.compat.category;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.compat.display.ReactorCrucibleDisplay;
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

public class ReactorCrucibleCategory implements DisplayCategory<ReactorCrucibleDisplay> {
    public static final CategoryIdentifier<ReactorCrucibleDisplay> REACTOR_CRUCIBLE =
            CategoryIdentifier.of(ArknightsEndfield.MOD_ID, "reactor_crucible");

    private static final int COLUMNS = 2;
    private static final int PITCH = 20;

    @Override
    public CategoryIdentifier<? extends ReactorCrucibleDisplay> getCategoryIdentifier() {
        return REACTOR_CRUCIBLE;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("blockEntity.reactor_crucible");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.REACTOR_CRUCIBLE.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(ReactorCrucibleDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));

        int inputRows = (display.getInputEntries().size() + COLUMNS - 1) / COLUMNS;
        int outputRows = (display.getOutputEntries().size() + COLUMNS - 1) / COLUMNS;
        int rows = Math.max(inputRows, outputRows);
        int gridHeight = (rows - 1) * PITCH;

        Point start = new Point(bounds.getCenterX() - 54, bounds.getCenterY() - (gridHeight + 18) / 2);

        for (int i = 0; i < display.getInputEntries().size(); i++) {
            widgets.add(Widgets.createSlot(new Point(
                            start.x + (i % COLUMNS) * PITCH,
                            start.y + (i / COLUMNS) * PITCH))
                    .entries(display.getInputEntries().get(i))
                    .markInput());
        }

        widgets.add(Widgets.createArrow(new Point(start.x + 44, start.y + 1 + gridHeight / 2)));

        int outputX = start.x + 70;
        for (int i = 0; i < display.getOutputEntries().size(); i++) {
            widgets.add(Widgets.createSlot(new Point(
                            outputX + (i % COLUMNS) * PITCH,
                            start.y + (i / COLUMNS) * PITCH))
                    .entries(display.getOutputEntries().get(i))
                    .markOutput());
        }
        return widgets;
    }
}
