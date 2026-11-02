package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.screen.custom.screenHandler.FluidPumpScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

public class FluidPumpScreen extends HandledScreen<FluidPumpScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/fluid_pump.png");
    private static final Identifier WATER_TEXTURE = new Identifier("minecraft", "textures/block/water_still.png");

    // 能量条位置（纹理中黄色槽位于 x=158, y=11, 8x64）
    private static final int POWER_BAR_X = 158;
    private static final int POWER_BAR_Y = 11;
    private static final int POWER_BAR_WIDTH = 8;
    private static final int POWER_BAR_HEIGHT = 64;
    private static final int MAX_STORED_POWER = 10000;

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

            // 按罐内流体类型取色（复用 FluidTankScreen 的取色逻辑）
            Fluid fluid = Registries.FLUID.get(handler.getFluidId());
            float[] fluidColor = FluidTankScreen.getFluidColor(fluid);

            RenderSystem.setShaderTexture(0, WATER_TEXTURE);
            RenderSystem.setShaderColor(fluidColor[0], fluidColor[1], fluidColor[2], fluidColor[3]);

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

        // 能量条：纹理已绘出满格黄色槽，用灰色覆盖未储存（顶部）部分（参考 RefiningUnitScreen）
        int storedPower = handler.getStoredPower();
        int coverHeight = (int) ((MAX_STORED_POWER - storedPower) / (float) MAX_STORED_POWER * POWER_BAR_HEIGHT);
        if (coverHeight <= 0) return;
        context.drawTexture(TEXTURE, x + 158, y + 11, 204, 0, 8, coverHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);

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
                Fluid fluid = Registries.FLUID.get(handler.getFluidId());
                Text fluidName = Text.translatable(fluid.getDefaultState().getBlockState().getBlock().getTranslationKey());
                tooltip.add(Text.translatable("tooltip.water_pump.fluid_type",
                        fluidName, amount / 1000, capacity / 1000).formatted(Formatting.AQUA));
            } else {
                tooltip.add(Text.translatable("tooltip.water_pump.empty")
                        .formatted(Formatting.GRAY));
            }

            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }

        // 能量条 tooltip
        int powerX = x + POWER_BAR_X;
        int powerY = y + POWER_BAR_Y;
        if (mouseX >= powerX && mouseX <= powerX + POWER_BAR_WIDTH &&
                mouseY >= powerY && mouseY <= powerY + POWER_BAR_HEIGHT) {
            int storedPower = handler.getStoredPower();
            List<Text> tooltip = new ArrayList<>();
            tooltip.add(Text.translatable("tooltip.fluid_pump.stored_power",
                    storedPower).formatted(Formatting.YELLOW));
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        int fluidX = x + 64;
        int fluidY = y + 11;
        int fluidWidth = 48;
        int fluidHeight = 64;

        if (mouseX >= fluidX && mouseX <= fluidX + fluidWidth &&
                mouseY >= fluidY && mouseY <= fluidY + fluidHeight) {
            assert client != null;
            assert client.player != null;
            if (client.player.currentScreenHandler.getCursorStack().getItem() == Items.BUCKET) {
                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                buf.writeBlockPos(handler.getBlockEntityPos());
                ClientPlayNetworking.send(ModNetWorking.FILL_BUCKET_FROM_PUMP, buf);
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}
