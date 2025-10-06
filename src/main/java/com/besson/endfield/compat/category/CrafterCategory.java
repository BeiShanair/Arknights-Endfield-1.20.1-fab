package com.besson.endfield.compat.category;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.compat.display.CrafterDisplay;
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

public class CrafterCategory implements DisplayCategory<CrafterDisplay> {
    public static final CategoryIdentifier<CrafterDisplay> CRAFTER =
            CategoryIdentifier.of(ArknightsEndfield.MOD_ID, "crafter");
    @Override
    public CategoryIdentifier<? extends CrafterDisplay> getCategoryIdentifier() {
        return CRAFTER;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("blockEntity.crafter");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CRAFTER.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(CrafterDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();

        widgets.add(Widgets.createRecipeBase(bounds));

        Point start = new Point(bounds.getCenterX() - 60, bounds.getCenterY() - 8);

        List<EntryIngredient> inputs = display.getInputEntries();
        for (int i = 0; i < inputs.size(); i++) {
            widgets.add(
                    Widgets.createSlot(new Rectangle(start.x + i * 20, start.y, 18, 18))
                            .entries(inputs.get(i))
                            .markInput());
        }

        widgets.add(Widgets.createSlot(new Rectangle(start.x + 100, start.y, 18, 18))
                .entries(display.getOutputEntries().get(0))
                .markOutput());

        widgets.add(Widgets.createArrow(new Point(start.x + 50, start.y + 1)));
        return widgets;
    }
}
