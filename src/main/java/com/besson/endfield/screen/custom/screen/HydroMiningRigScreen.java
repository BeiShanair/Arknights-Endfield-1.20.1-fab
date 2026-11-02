package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.resourcing.HydroMiningRigBlockEntity;
import com.besson.endfield.screen.custom.screenHandler.HydroMiningRigScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class HydroMiningRigScreen extends BaseRigScreen<HydroMiningRigScreenHandler, HydroMiningRigBlockEntity> {
    private static final Identifier WATER_TEXTURE = new Identifier("minecraft", "textures/block/water_still.png");
    /** 水罐显示区域（相对 GUI 左上角），绘制在贴图左侧留白处。 */
    private static final int TANK_X = 20;
    private static final int TANK_Y = 15;
    private static final int TANK_W = 18;
    private static final int TANK_H = 54;

    public HydroMiningRigScreen(HydroMiningRigScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected Identifier setTexture() {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/021_no_power.png");
    }

    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 68, y + 41, 176, 0, handler.getScaledProgress(), 8);
        }
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        super.drawBackground(context, delta, mouseX, mouseY);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        drawWaterTank(context, x, y);
    }

    private void drawWaterTank(DrawContext context, int x, int y) {
        int tankX = x + TANK_X;
        int tankY = y + TANK_Y;

        // 罐体外框与内部
        context.fill(tankX - 1, tankY - 1, tankX + TANK_W + 1, tankY + TANK_H + 1, 0xFF3A3F44);
        context.fill(tankX, tankY, tankX + TANK_W, tankY + TANK_H, 0xFF8b8b8b);

        int amount = handler.getWaterAmount();
        int capacity = handler.getWaterCapacity();
        if (amount <= 0 || capacity <= 0) return;

        int innerX = tankX + 1;
        int innerW = TANK_W - 2;
        int innerBottom = tankY + TANK_H - 1;
        int innerH = TANK_H - 2;
        int waterH = (int) ((long) amount * innerH / capacity);
        if (waterH <= 0) return;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, WATER_TEXTURE);
        RenderSystem.setShaderColor(0.2f, 0.4f, 1.0f, 0.7f);

        int drawY = innerBottom - waterH;
        while (drawY < innerBottom) {
            int h = Math.min(32, innerBottom - drawY);
            context.drawTexture(WATER_TEXTURE, innerX, drawY, 0, 0, innerW, h, 32, 32);
            drawY += h;
        }

        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        if (mouseX >= x + TANK_X && mouseX <= x + TANK_X + TANK_W
                && mouseY >= y + TANK_Y && mouseY <= y + TANK_Y + TANK_H) {

            int amount = handler.getWaterAmount();
            int capacity = handler.getWaterCapacity();

            List<Text> tooltip = new ArrayList<>();
            if (amount > 0) {
                tooltip.add(Text.translatable("tooltip.water_pump.fluid",
                        amount / 1000, capacity / 1000).formatted(Formatting.AQUA));
            } else {
                tooltip.add(Text.translatable("tooltip.water_pump.empty")
                        .formatted(Formatting.GRAY));
            }
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }
}
