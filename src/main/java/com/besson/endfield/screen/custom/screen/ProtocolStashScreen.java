package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.logicitis.ProtocolStashBlockEntity;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.screen.ToggleIconButton;
import com.besson.endfield.screen.custom.screenHandler.ProtocolStashScreenHandler;
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

public class ProtocolStashScreen extends HandledScreen<ProtocolStashScreenHandler> {
    private final ProtocolStashBlockEntity entity;
    private static final Identifier STORAGE_TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/generic_54.png");

    public ProtocolStashScreen(ProtocolStashScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.entity = (ProtocolStashBlockEntity) handler.entity;
    }

    @Override
    protected void init() {
        super.init();
        this.addDrawableChild(new ToggleIconButton(x + 150, y + 1, handler::isEnabled,
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
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        
        RenderSystem.setShaderTexture(0, STORAGE_TEXTURE);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(STORAGE_TEXTURE, x, y, 0, 0, this.backgroundWidth, 3 * 18 + 17);
        context.drawTexture(STORAGE_TEXTURE, x, y + 3 * 18 + 17, 0, 126, this.backgroundWidth, 96);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);
    }
}
