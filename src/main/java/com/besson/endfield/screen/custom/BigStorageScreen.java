package com.besson.endfield.screen.custom;

import com.besson.endfield.ArknightsEndfield;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BigStorageScreen extends HandledScreen<BigStorageScreenHandler> {
    private static final Identifier STORAGE_TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/generic_54.png");

    public BigStorageScreen(BigStorageScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 222;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);


        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - 222) / 2;

        RenderSystem.setShaderTexture(0, STORAGE_TEXTURE);
        context.drawTexture(STORAGE_TEXTURE, x, y, 0, 0, this.backgroundWidth, 125);
        context.drawTexture(STORAGE_TEXTURE, x, y + 125, 0, 126, this.backgroundWidth, 96);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (amount > 0) {
            handler.previousPage();
        } else if (amount < 0) {
            handler.nextPage();
        }
        return true;
    }
}
