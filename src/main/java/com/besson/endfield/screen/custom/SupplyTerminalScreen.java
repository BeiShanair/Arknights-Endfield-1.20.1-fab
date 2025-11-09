package com.besson.endfield.screen.custom;

import com.besson.endfield.blockentity.custom.SupplyTerminalBlockEntity;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.trade.SupplyTrade;
import com.mojang.blaze3d.systems.RenderSystem;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class SupplyTerminalScreen extends HandledScreen<SupplyTerminalScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("minecraft", "textures/gui/container/villager2.png");
    private final SupplyTerminalBlockEntity entity;
    private int selectedIndex = -1;
    private int scrollOffset = 0;

    public SupplyTerminalScreen(SupplyTerminalScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.entity = (SupplyTerminalBlockEntity) handler.inventory;
        this.backgroundWidth = 276;
        this.playerInventoryTitleX = 107;
    }

    @Override
    protected void init() {
        super.init();
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Submit"), b -> {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            buf.writeBlockPos(entity.getPos());
            ClientPlayNetworking.send(ModNetWorking.SUPPLY_TERMINAL_SYNC_PACKET_ID, buf);
        }).dimensions(x + 150, y + 60, 40, 20).build());
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - 276) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0.0F, 0.0F, 276, this.backgroundHeight, 512, 256);

        drawTradeList(context, x + 5, y + 18);
        drawLevelInfo(context, x, y);
    }

    private void drawTradeList(DrawContext context, int x, int y) {
        List<SupplyTrade> trades = handler.getAvailableTrades();
        int displayCount = Math.min(3, trades.size() - scrollOffset);

        for (int i = 0; i < displayCount; i++) {
            int index = i + scrollOffset;
            SupplyTrade trade = trades.get(index);
            int entryY = y + i * 28;

            context.fill(x, entryY, x + 95, entryY + 26, 0xFFCCCCCC);

            ItemStack displayInput = trade.input.copy();
            displayInput.setCount(trade.amount);
            context.drawItem(displayInput, x + 5, entryY + 5);
            context.drawItemInSlot(textRenderer, displayInput, x + 5, entryY + 5);

            context.drawText(textRenderer, Text.literal(">"), x + 25, entryY + 8, 0x404040, false);

            context.drawItem(trade.reward, x + 35, entryY + 5);
            context.drawItemInSlot(textRenderer, trade.reward, x + 35, entryY + 5);

            String expText = "+ " + trade.ex + " EXP.";
            context.drawText(textRenderer, Text.literal(expText), x + 55, entryY + 8, 0x404040, false);

            if (index == selectedIndex) {
                context.fill(x, entryY, x + 120, entryY + 1, 0xFF0000FF);
                context.fill(x, entryY + 25, x + 120, entryY + 26, 0xFF0000FF);
                context.fill(x, entryY, x + 1, entryY + 26, 0xFF0000FF);
                context.fill(x + 119, entryY, x + 120, entryY + 26, 0xFF0000FF);
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);
    }

    private void drawLevelInfo(DrawContext context, int x, int y) {
        int level = handler.getLevel();
        int exp = handler.getTradePoints();
        int expToNext = switch (level) {
            case 1 -> 100;
            case 2 -> 300;
            case 3 -> 600;
            default -> 0;
        };

        int barX = x + 136;
        int barY = y + 16;
        int barWidth = 102;
        int barHeight = 5;

        context.drawTexture(TEXTURE, barX, barY, 0, 0.0F, 186, barWidth, barHeight, 512, 256);

        if (level < 4 && expToNext > 0) {
            int filled = MathHelper.floor((float) exp / expToNext * barWidth);
            if (filled > 0) {
                context.drawTexture(TEXTURE, barX, barY, 0, 0.0F, 191, filled, barHeight, 512, 256);
            }
        }
        context.drawText(this.textRenderer,
                Text.translatable("screen.supply_terminal.level", level),
                barX, barY - 10, 0x404040, false);
    }
}
