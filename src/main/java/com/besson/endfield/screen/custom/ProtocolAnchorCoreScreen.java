package com.besson.endfield.screen.custom;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.utils.ProtocolAnchorCoreStatus;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ProtocolAnchorCoreScreen extends HandledScreen<ProtocolAnchorCoreScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/protocol_anchor_core.png");

    private final BlockPos pos;

    public ProtocolAnchorCoreScreen(ProtocolAnchorCoreScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.pos = handler.getPos();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }

    private void drawBackground(DrawContext context, float delta, int mouseX, int mouseY, ProtocolAnchorCoreStatus status) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        // 进度条
        int barWidth = 120;
        int barHeight = 12;
        int filled = (int) ((double) status.buffer() / status.maxBuffer() * barWidth);

        context.fill(x + 25, y + 120, x + 25 + barWidth, y + 120  + barHeight, 0xFF555555); // 背景
        context.fill(x + 25, y + 120, x + 25 + filled, y + 120 + barHeight, 0xFF2E7D32);   // 电量
        context.drawBorder(x + 25, y + 120, barWidth, barHeight, 0xFF000000); // 边框

        // 数值文字（核心缓冲）
        context.drawText(this.textRenderer,
                Text.translatable("screen.protocol_core.buffer", status.buffer(), status.maxBuffer()),
                x + 8, y + 20, 0x404040, false);
        context.drawText(this.textRenderer,
                Text.translatable("screen.protocol_core.base_power", status.basePower()),
                x + 8, y + 30, 0xFFFFFF,false);
        context.drawText(this.textRenderer,
                Text.translatable("screen.protocol_core.extra_power", status.extraPower()),
                x + 8, y + 40, 0xFF8000,false);
        context.drawText(this.textRenderer,
                Text.translatable("screen.protocol_core.load", status.loadNodeNum()),
                x + 8, y + 50, 0xFF0000,false);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        drawBackground(context, delta, mouseX, mouseY, handler.getStatus());
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 4210752, false);
    }
}
