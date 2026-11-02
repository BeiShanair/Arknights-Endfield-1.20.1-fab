package com.besson.endfield.compat.category;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.compat.display.HydroMiningRigDisplay;
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

public class HydroMiningRigCategory implements DisplayCategory<HydroMiningRigDisplay> {
    public static final CategoryIdentifier<HydroMiningRigDisplay> HYDRO_MINING_RIG =
            CategoryIdentifier.of(ArknightsEndfield.MOD_ID, "hydro_mining_rig");

    @Override
    public CategoryIdentifier<? extends HydroMiningRigDisplay> getCategoryIdentifier() {
        return HYDRO_MINING_RIG;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("blockEntity.hydro_mining_rig");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.HYDRO_MINING_RIG.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(HydroMiningRigDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 41, bounds.getCenterY() - 8);

        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 25, startPoint.y)));
        if (!display.getInputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(
                            new Point(startPoint.x, startPoint.y))
                    .entries(display.getInputEntries().get(0))
                    .markInput());
        }
        if (display.getInputEntries().size() >= 2) {
            Point waterPoint = new Point(startPoint.x - 22, startPoint.y);
            widgets.add(Widgets.createSlot(waterPoint)
                    .entries(display.getInputEntries().get(1))
                    .markInput());
        }
        if (!display.getOutputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 55, startPoint.y))
                    .entries(display.getOutputEntries().get(0))
                    .markOutput());
        }
        return widgets;
    }
}
