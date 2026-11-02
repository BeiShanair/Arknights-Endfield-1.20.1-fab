package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production1.RefiningUnitBlockEntity;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.screen.FluidModeButton;
import com.besson.endfield.screen.ToggleIconButton;
import com.besson.endfield.screen.custom.screenHandler.RefiningUnitScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class RefiningUnitScreen extends BaseIOScreen<RefiningUnitScreenHandler, RefiningUnitBlockEntity> {
    private static final Identifier WATER_TEXTURE = new Identifier("minecraft", "textures/block/water_still.png");
    private static final int FLUID_BAR_X = 40;
    private static final int FLUID_BAR_Y = 11;
    private static final int FLUID_BAR_WIDTH = 16;
    private static final int FLUID_BAR_HEIGHT = 64;

    private static final int SEWAGE_BAR_X = 120;
    private static final int SEWAGE_BAR_Y = 11;
    private static final int SEWAGE_BAR_WIDTH = 16;
    private static final int SEWAGE_BAR_HEIGHT = 64;

    public RefiningUnitScreen(RefiningUnitScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.addDrawableChild(new FluidModeButton(textRenderer, x + 140, y + 30, handler::nowFluidMode,
                button -> {
                    PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                    buf.writeBlockPos(entity.getPos());
                    boolean newFluidMode = !handler.nowFluidMode();
                    buf.writeBoolean(newFluidMode);
                    ClientPlayNetworking.send(ModNetWorking.FLUID_MODE_CHANGE, buf);
                }));
    }

    @Override
    protected Identifier setTexture() {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/121.png");
    }

    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, 8, handler.getScaledProgress());
        }

        renderFluidBar(context, x, y);
        renderSewageBar(context, x, y);
        renderStoredPowerBar(context, x, y);
    }

    private void renderStoredPowerBar(DrawContext context, int x, int y) {
        int storedPower = handler.getStoredPower();
        int powerHeight = (int) ((10000F - storedPower) / 10000f * 64);
        if (powerHeight <= 0) return;
        context.drawTexture(TEXTURE, x + 158, y + 11, 187, 0, 8, powerHeight);
    }

    private void renderFluidBar(DrawContext context, int x, int y) {
        int fluidX = x + FLUID_BAR_X;
        int fluidY = y + FLUID_BAR_Y;
        
        if (handler.nowFluidMode()) {
            context.fill(fluidX - 1, fluidY - 1, fluidX + FLUID_BAR_WIDTH + 1, fluidY + FLUID_BAR_HEIGHT + 1, 0xFF3A3F44);
            context.fill(fluidX, fluidY, fluidX + FLUID_BAR_WIDTH, fluidY + FLUID_BAR_HEIGHT, 0xFF8b8b8b);
        }

        int capacity = handler.getFluidCapacity();
        if (capacity <= 0) return;

        int amount = handler.getFluidAmount();
        
        int innerX = fluidX + 1;
        int innerW = FLUID_BAR_WIDTH - 2;
        int innerBottom = fluidY + FLUID_BAR_HEIGHT - 1;
        int innerH = FLUID_BAR_HEIGHT - 2;
        int innerFluidHeight = (int) (amount / (float) capacity * innerH);
        if (innerFluidHeight <= 0) return;
        int drawY = innerBottom - innerFluidHeight;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        
        Fluid fluid = Registries.FLUID.get(handler.getFluidId());
        float[] fluidColor = FluidTankScreen.getFluidColor(fluid);
        RenderSystem.setShaderColor(fluidColor[0], fluidColor[1], fluidColor[2], fluidColor[3]);

        for (int yOffset = 0; yOffset < innerFluidHeight; yOffset += 16) {
            int drawHeight = Math.min(16, innerFluidHeight - yOffset);
            context.drawTexture(WATER_TEXTURE, innerX, drawY + yOffset,
                    0, 0, innerW, drawHeight, 16, 16);
        }

        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }

    private void renderSewageBar(DrawContext context, int x, int y) {
        int sewageX = x + SEWAGE_BAR_X;
        int sewageY = y + SEWAGE_BAR_Y;
        
        if (handler.nowFluidMode()) {
            context.fill(sewageX - 1, sewageY - 1, sewageX + SEWAGE_BAR_WIDTH + 1, sewageY + SEWAGE_BAR_HEIGHT + 1, 0xFF3A3F44);
            context.fill(sewageX, sewageY, sewageX + SEWAGE_BAR_WIDTH, sewageY + SEWAGE_BAR_HEIGHT, 0xFF8b8b8b);
        }
        
        int capacity = handler.getSewageCapacity();
        if (capacity <= 0) return;

        int amount = handler.getSewageAmount();
        
        int innerX = sewageX + 1;
        int innerW = SEWAGE_BAR_WIDTH - 2;
        int innerBottom = sewageY + SEWAGE_BAR_HEIGHT - 1;
        int innerH = SEWAGE_BAR_HEIGHT - 2;
        int innerFluidHeight = (int) (amount / (float) capacity * innerH);
        if (innerFluidHeight <= 0) return;
        int drawY = innerBottom - innerFluidHeight;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        
        Fluid sewageFluid = Registries.FLUID.get(handler.getSewageId());
        float[] sewageColor = FluidTankScreen.getFluidColor(sewageFluid);
        RenderSystem.setShaderColor(sewageColor[0], sewageColor[1], sewageColor[2], sewageColor[3]);

        for (int yOffset = 0; yOffset < innerFluidHeight; yOffset += 16) {
            int drawHeight = Math.min(16, innerFluidHeight - yOffset);
            context.drawTexture(WATER_TEXTURE, innerX, drawY + yOffset,
                    0, 0, innerW, drawHeight, 16, 16);
        }

        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        
        int fluidX = x + FLUID_BAR_X;
        int fluidY = y + FLUID_BAR_Y;
        if (mouseX >= fluidX && mouseX <= fluidX + FLUID_BAR_WIDTH &&
                mouseY >= fluidY && mouseY <= fluidY + FLUID_BAR_HEIGHT) {
            int amount = handler.getFluidAmount();
            int capacity = handler.getFluidCapacity();

            List<Text> tooltip = new ArrayList<>();
            if (amount > 0) {
                tooltip.add(Text.literal(amount / 1000 + " / " + capacity / 1000 + " B")
                        .formatted(Formatting.AQUA));
            } else {
                tooltip.add(Text.translatable("tooltip.refining_unit.empty")
                        .formatted(Formatting.GRAY));
            }
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
        
        int sewageX = x + SEWAGE_BAR_X;
        int sewageY = y + SEWAGE_BAR_Y;
        if (mouseX >= sewageX && mouseX <= sewageX + SEWAGE_BAR_WIDTH &&
                mouseY >= sewageY && mouseY <= sewageY + SEWAGE_BAR_HEIGHT) {
            int amount = handler.getSewageAmount();
            int capacity = handler.getSewageCapacity();

            List<Text> tooltip = new ArrayList<>();
            if (amount > 0) {
                tooltip.add(Text.translatable("tooltip.refining_unit.sewage",
                        amount / 1000, capacity / 1000).formatted(Formatting.DARK_GREEN));
            } else {
                tooltip.add(Text.translatable("tooltip.refining_unit.sewage_empty")
                        .formatted(Formatting.GRAY));
            }
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }

        int powerX = x + 158;
        int powerY = y + 11;
        if (mouseX >= powerX && mouseX <= powerX + 8 &&
                mouseY >= powerY && mouseY <= powerY + 64) {
            int storedPower = handler.getStoredPower();
            List<Text> tooltip = new ArrayList<>();
            tooltip.add(Text.translatable("tooltip.refining_unit.stored_power",
                    storedPower).formatted(Formatting.YELLOW));
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }
}
