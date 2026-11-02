package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production2.SeparatingUnitBlockEntity;
import com.besson.endfield.screen.custom.screenHandler.SeparatingUnitScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class SeparatingUnitScreen extends BaseIOScreen<SeparatingUnitScreenHandler, SeparatingUnitBlockEntity> {
    private static final Identifier WATER_TEXTURE = new Identifier("minecraft", "textures/block/water_still.png");
    private static final int FLUID_BAR_X = 120;
    private static final int FLUID_BAR_Y = 11;
    private static final int FLUID_BAR_WIDTH = 16;
    private static final int FLUID_BAR_HEIGHT = 64;

    public SeparatingUnitScreen(SeparatingUnitScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
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
        
        context.fill(fluidX - 1, fluidY - 1, fluidX + FLUID_BAR_WIDTH + 1, fluidY + FLUID_BAR_HEIGHT + 1, 0xFF3A3F44);
        context.fill(fluidX, fluidY, fluidX + FLUID_BAR_WIDTH, fluidY + FLUID_BAR_HEIGHT, 0xFF8b8b8b);

        int capacity = handler.getFluidCapacity();
        if (capacity <= 0) return;

        int amount = handler.getFluidAmount();
        if (amount <= 0) return;
        
        int innerX = fluidX + 1;
        int innerW = FLUID_BAR_WIDTH - 2;
        int innerBottom = fluidY + FLUID_BAR_HEIGHT - 1;
        int innerH = FLUID_BAR_HEIGHT - 2;
        int innerFluidHeight = (int) (amount / (float) capacity * innerH);
        if (innerFluidHeight <= 0) return;
        int drawY = innerBottom - innerFluidHeight;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        Fluid fluid = Registries.FLUID.get(handler.getFluidRawId());
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
                Fluid fluid = Registries.FLUID.get(handler.getFluidRawId());
                Text fluidName = Text.translatable(fluid.getDefaultState().getBlockState().getBlock().getTranslationKey());
                tooltip.add(Text.translatable("tooltip.separating_unit.fluid",
                        fluidName, amount / 1000, capacity / 1000).formatted(Formatting.AQUA));
            } else {
                tooltip.add(Text.translatable("tooltip.separating_unit.fluid_empty")
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
            tooltip.add(Text.translatable("tooltip.separating_unit.stored_power",
                    storedPower).formatted(Formatting.YELLOW));
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }
}
