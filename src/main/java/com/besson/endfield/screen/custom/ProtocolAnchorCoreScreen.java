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

// TODO: 美化GUI界面，实现类似原作游戏的GUI，环形，动态效果等
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

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        context.drawText(this.textRenderer,
                Text.translatable("screen.protocol_core.buffer"),
                x + 70, y + 55, 0x404040, false);
        context.drawText(this.textRenderer,
                Text.translatable("screen.protocol_core.storedEnergy", handler.storedEnergy),
                x + 65, y + 70, 0x404040, false);
        context.drawText(this.textRenderer,
                Text.translatable("screen.protocol_core.max", 100000),
                x + 70, y + 85, 0x404040, false);
        context.drawText(this.textRenderer,
                Text.translatable("screen.protocol_core.generated", handler.totalGenerated),
                x + 20, y + 134, 0xFFFFFF,false);
        context.drawText(this.textRenderer,
                Text.translatable("screen.protocol_core.consumer", handler.totalDemand),
                x + 102, y + 18, 0xFF8000,false);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 4210752, false);
    }
}
