package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production1.ShreddingUnitBlockEntity;
import com.besson.endfield.screen.custom.screenHandler.ShreddingUnitScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ShreddingUnitScreen extends BaseIOScreen<ShreddingUnitScreenHandler, ShreddingUnitBlockEntity> {
    public ShreddingUnitScreen(ShreddingUnitScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected Identifier setTexture() {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/shredding_unit.png");
    }

    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()){
            context.drawTexture(TEXTURE,x + 85, y + 30, 176,0,8,handler.getScaledProgress());
        }
    }
}
