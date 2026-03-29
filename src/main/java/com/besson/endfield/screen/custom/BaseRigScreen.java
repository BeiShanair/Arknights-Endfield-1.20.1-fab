package com.besson.endfield.screen.custom;

import com.besson.endfield.blockentity.custom.resourcing.BaseRigBlockEntity;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.screen.ToggleIconButton;
import com.mojang.blaze3d.systems.RenderSystem;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class BaseRigScreen<T extends BaseRigScreenHandler<B>, B extends BaseRigBlockEntity<?>> extends HandledScreen<T> {
    protected final Identifier TEXTURE = setTexture();
    protected final B entity;
    public BaseRigScreen(T handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.entity = (B) handler.entity;
        
    }
    
    protected abstract Identifier setTexture();

    @Override
    protected void init() {
        super.init();
        this.addDrawableChild(new ToggleIconButton(x + 150, y + 30, handler::isEnabled,
                button -> {
                    PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                    buf.writeBlockPos(entity.getPos());
                    boolean newEnableState = !handler.isEnabled();
                    handler.setEnabled(newEnableState);
                    buf.writeBoolean(newEnableState);
                    ClientPlayNetworking.send(ModNetWorking.SWITCH_PACKET_ID, buf);
                }));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);
    }

    protected abstract void renderProgressArrow(DrawContext context, int x, int y);

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
    }
}
