package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production2.GearingUnitBlockEntity;
import com.besson.endfield.screen.custom.screenHandler.GearingUnitScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GearingUnitScreen extends BaseIOScreen<GearingUnitScreenHandler, GearingUnitBlockEntity> {
    public GearingUnitScreen(GearingUnitScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected Identifier setTexture() {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/221.png");
    }

    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 75, y + 40, 176, 0, handler.getScaledProgress(), 8);
        }
        renderStoredPowerBar(context, x, y);
    }

    private void renderStoredPowerBar(DrawContext context, int x, int y) {
        int storedPower = handler.getStoredPower();
        int powerHeight = (int) ((10000F - storedPower) / 10000f * 64);
        if (powerHeight <= 0) return;
        context.drawTexture(TEXTURE, x + 158, y + 11, 204, 0, 8, powerHeight);
    }
}
