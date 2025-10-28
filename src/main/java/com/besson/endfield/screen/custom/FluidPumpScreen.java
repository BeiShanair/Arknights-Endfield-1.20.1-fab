package com.besson.endfield.screen.custom;

import com.besson.endfield.ArknightsEndfield;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class FluidPumpScreen extends HandledScreen<FluidPumpScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/fluid_pump.png");
    private static final Identifier WATER_TEXTURE = new Identifier("minecraft", "textures/block/water_still.png");

    public FluidPumpScreen(FluidPumpScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(1, TEXTURE);

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);

        int fluidHeight = (int) (handler.getFluidAmount() / (float) handler.getFluidCapacity() * 64);
        if (fluidHeight > 0) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            RenderSystem.setShaderTexture(0, WATER_TEXTURE);
            RenderSystem.setShaderColor(0.2f, 0.4f, 1.0f, 0.7f);

            int fluidX = x + 64;
            int fluidY = y + 75 - fluidHeight;
            for (int yOffset = 0; yOffset < fluidHeight; yOffset += 32) {
                int drawHeight = Math.min(32, fluidHeight - yOffset);
                context.drawTexture(WATER_TEXTURE, fluidX, fluidY + yOffset,
                        0, 0, 48, drawHeight, 32, 32);
            }

            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        int fluidX = x + 64;
        int fluidY = y + 11;
        int fluidWidth = 48;
        int fluidHeight = 64;

        if (mouseX >= fluidX && mouseX <= fluidX + fluidWidth &&
                mouseY >= fluidY && mouseY <= fluidY + fluidHeight) {

            // 获取流体信息
            int amount = handler.getFluidAmount();
            int capacity = handler.getFluidCapacity();

            // 构建提示文字
            List<Text> tooltip = new ArrayList<>();
            if (amount > 0) {
                tooltip.add(Text.translatable("tooltip.water_pump.fluid",
                        amount, capacity).formatted(Formatting.AQUA));
            } else {
                tooltip.add(Text.translatable("tooltip.water_pump.empty")
                        .formatted(Formatting.GRAY));
            }

            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }
}
