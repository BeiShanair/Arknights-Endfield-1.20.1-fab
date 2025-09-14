package com.besson.endfield.screen.custom;

import com.besson.endfield.ArknightsEndfield;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ThermalBankScreen extends HandledScreen<ThermalBankScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/thermal_bank.png");

    public ThermalBankScreen(ThermalBankScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressFire(context, x, y);
    }

    private void renderProgressFire(DrawContext context, int x, int y) {
        if (handler.isCrafting()){
            int totalFireHeight = 14; // 火焰总高度
            int fireHeight = handler.getScaledProgress(); // 当前火焰高度
            int fireYOffset = totalFireHeight - fireHeight; // 火焰顶部偏移

            context.drawTexture(TEXTURE,x + 80, y + 39 + fireYOffset, 176, fireYOffset, 14, fireHeight);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);
    }
}
