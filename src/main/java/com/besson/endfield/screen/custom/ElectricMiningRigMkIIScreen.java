package com.besson.endfield.screen.custom;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.ElectricMiningRigMkIIBlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ElectricMiningRigMkIIScreen extends BaseRigScreen<ElectricMiningRigMkIIScreenHandler, ElectricMiningRigMkIIBlockEntity> {
    public ElectricMiningRigMkIIScreen(ElectricMiningRigMkIIScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected Identifier setTexture() {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/electric_mining_rig_mk_ii.png");
    }

    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()){
            context.drawTexture(TEXTURE,x + 68, y + 41, 176, 0, handler.getScaledProgress(), 8);
        }
    }
}
