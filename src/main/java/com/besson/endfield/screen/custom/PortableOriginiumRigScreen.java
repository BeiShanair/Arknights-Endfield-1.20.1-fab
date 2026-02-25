package com.besson.endfield.screen.custom;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.PortableOriginiumRigBlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PortableOriginiumRigScreen extends BaseRigScreen<PortableOriginiumRigScreenHandler, PortableOriginiumRigBlockEntity> {
    public PortableOriginiumRigScreen(PortableOriginiumRigScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected Identifier setTexture() {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/portable_originium_rig.png");
    }

    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()){
            context.drawTexture(TEXTURE,x + 68, y + 41, 176,0, handler.getScaledProgress(), 8);
        }
    }
}
