package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production1.WaterTreatmentUnitBlockEntity;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.screen.ModScreens;
import com.besson.endfield.screen.ToggleIconButton;
import com.besson.endfield.screen.custom.screenHandler.WaterTreatmentUnitScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class WaterTreatmentUnitScreen extends HandledScreen<WaterTreatmentUnitScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/120.png");
    private static final Identifier WATER_TEXTURE = new Identifier("minecraft", "textures/block/water_still.png");

    private static final int FLUID_BAR_X = 40;
    private static final int FLUID_BAR_Y = 37;
    private static final int FLUID_BAR_WIDTH = 16;
    private static final int FLUID_BAR_HEIGHT = 16;

    private static final int POWER_BAR_X = 158;
    private static final int POWER_BAR_Y = 11;
    private static final int POWER_BAR_WIDTH = 8;
    private static final int POWER_BAR_HEIGHT = 64;

    private final WaterTreatmentUnitBlockEntity entity;

    public WaterTreatmentUnitScreen(WaterTreatmentUnitScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.entity = handler.entity;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        this.addDrawableChild(new ToggleIconButton(textRenderer, x + 140, y + 10, handler::isEnabled,
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
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        renderFluidTooltip(context, x, y, mouseX, mouseY);
        renderPowerTooltip(context, x, y, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
        renderFluidBar(context, x, y);
        renderStoredPowerBar(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 68, y + 41, 176, 0, handler.getScaledProgress(), 8);
        }
    }
    
    private void renderFluidBar(DrawContext context, int x, int y) {
        int capacity = handler.getFluidCapacity();
        if (capacity <= 0) return;

        int amount = handler.getFluidAmount();
        int fluidHeight = (int) (amount / (float) capacity * FLUID_BAR_HEIGHT);
        if (fluidHeight <= 0) return;
        int drawY = y + FLUID_BAR_Y + FLUID_BAR_HEIGHT - fluidHeight;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // 根据槽内流体类型计算渲染颜色（RGBA，各分量 0~1）
        Fluid fluid = Registries.FLUID.get(handler.getFluidId());
        float[] fluidColor = FluidTankScreen.getFluidColor(fluid);
        RenderSystem.setShaderColor(fluidColor[0], fluidColor[1], fluidColor[2], fluidColor[3]);

        context.drawTexture(WATER_TEXTURE, x + FLUID_BAR_X, drawY,
                0, 0, FLUID_BAR_WIDTH, fluidHeight, 16, 16);

        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }
    
    private void renderStoredPowerBar(DrawContext context, int x, int y) {
        int storedPower = handler.getStoredPower();
        int powerHeight = (int) ((10000F - storedPower) / 10000f * POWER_BAR_HEIGHT);
        if (powerHeight <= 0) return;
        context.drawTexture(TEXTURE, x + POWER_BAR_X, y + POWER_BAR_Y, 204, 0, POWER_BAR_WIDTH, powerHeight);
    }

    private void renderFluidTooltip(DrawContext context, int x, int y, int mouseX, int mouseY) {
        int fluidX = x + FLUID_BAR_X;
        int fluidY = y + FLUID_BAR_Y;
        if (mouseX >= fluidX && mouseX <= fluidX + FLUID_BAR_WIDTH &&
                mouseY >= fluidY && mouseY <= fluidY + FLUID_BAR_HEIGHT) {
            int amount = handler.getFluidAmount();
            int capacity = handler.getFluidCapacity();

            List<Text> tooltip = new ArrayList<>();
            if (amount > 0) {
                Fluid fluid = Registries.FLUID.get(handler.getFluidId());
                Text fluidName = Text.translatable(fluid.getDefaultState().getBlockState().getBlock().getTranslationKey());
                tooltip.add(Text.translatable("tooltip.water_treatment_unit.fluid",
                        fluidName, amount / 1000, capacity / 1000).formatted(Formatting.AQUA));
            } else {
                tooltip.add(Text.translatable("tooltip.water_treatment_unit.empty")
                        .formatted(Formatting.GRAY));
            }
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }

    private void renderPowerTooltip(DrawContext context, int x, int y, int mouseX, int mouseY) {
        int powerX = x + POWER_BAR_X;
        int powerY = y + POWER_BAR_Y;
        if (mouseX >= powerX && mouseX <= powerX + POWER_BAR_WIDTH &&
                mouseY >= powerY && mouseY <= powerY + POWER_BAR_HEIGHT) {
            int storedPower = handler.getStoredPower();
            List<Text> tooltip = new ArrayList<>();
            tooltip.add(Text.translatable("tooltip.water_treatment_unit.stored_power",
                    storedPower).formatted(Formatting.YELLOW));
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }
}
