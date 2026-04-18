package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production2.GrindingUnitBlockEntity;
import com.besson.endfield.screen.custom.screenHandler.GrindingUnitScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GrindingUnitScreen extends BaseIOScreen<GrindingUnitScreenHandler, GrindingUnitBlockEntity> {
    public GrindingUnitScreen(GrindingUnitScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected Identifier setTexture() {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/grinding_unit.png");
    }

    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 75, y + 40, 176, 0, handler.getScaledProgress(), 8);
        }
    }
}
