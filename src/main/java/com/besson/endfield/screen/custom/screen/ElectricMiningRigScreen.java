package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.resourcing.ElectricMiningRigBlockEntity;
import com.besson.endfield.screen.custom.screenHandler.ElectricMiningRigScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ElectricMiningRigScreen extends BaseRigScreen<ElectricMiningRigScreenHandler, ElectricMiningRigBlockEntity> {

    public ElectricMiningRigScreen(ElectricMiningRigScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected Identifier setTexture() {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/021.png");
    }

    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()){
            context.drawTexture(TEXTURE,x + 68, y + 41, 176,0, handler.getScaledProgress(), 8);
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
