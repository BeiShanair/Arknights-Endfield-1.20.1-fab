package com.besson.endfield.screen.custom;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.FillingUnitBlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FillingUnitScreen extends BaseIOScreen<FillingUnitScreenHandler, FillingUnitBlockEntity> {
    public FillingUnitScreen(FillingUnitScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected Identifier setTexture() {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/filling_unit.png");
    }
    
    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 75, y + 40, 176, 0, handler.getScaledProgress(), 8);
        }
    }
}
