package com.besson.endfield.screen.custom;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.PackagingUnitBlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PackagingUnitScreen extends BaseIOScreen<PackagingUnitScreenHandler, PackagingUnitBlockEntity> {
    public PackagingUnitScreen(PackagingUnitScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected Identifier setTexture() {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/packaging_unit.png");
    }

    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, handler.getScaledProgress(), 8);
        }
    }
}
