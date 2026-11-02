package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production2.FillingUnitBlockEntity;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.screen.FluidModeButton;
import com.besson.endfield.screen.custom.screenHandler.FillingUnitScreenHandler;
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

public class FillingUnitScreen extends BaseIOScreen<FillingUnitScreenHandler, FillingUnitBlockEntity> {
    private static final Identifier WATER_TEXTURE = new Identifier("minecraft", "textures/block/water_still.png");
    
    private static final int FLUID_SLOT_X = 47;
    private static final int FLUID_SLOT_Y = 49;
    private static final int SLOT_SIZE = 16;

    public FillingUnitScreen(FillingUnitScreenHandler handler, PlayerInventory inventory, Text title) {
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
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/221.png");
    }

    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 75, y + 40, 176, 0, handler.getScaledProgress(), 8);
        }
        renderStoredPowerBar(context, x, y);
        renderSlotFluid(context, x, y);
    }

    private void renderStoredPowerBar(DrawContext context, int x, int y) {
        int storedPower = handler.getStoredPower();
        int powerHeight = (int) ((10000F - storedPower) / 10000f * 64);
        if (powerHeight <= 0) return;
        context.drawTexture(TEXTURE, x + 158, y + 11, 204, 0, 8, powerHeight);
    }
    
    private void renderSlotFluid(DrawContext context, int x, int y) {
        if (!handler.nowFluidMode()) return;

        int capacity = handler.getFluidCapacity();
        int amount = handler.getFluidAmount();
        if (capacity <= 0 || amount <= 0) return;

        int fluidX = x + FLUID_SLOT_X;
        int fluidY = y + FLUID_SLOT_Y;
        int fluidHeight = Math.max(1, (int) (amount / (float) capacity * SLOT_SIZE));
        if (fluidHeight > SLOT_SIZE) fluidHeight = SLOT_SIZE;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        
        Fluid fluid = Registries.FLUID.get(handler.getFluidId());
        float[] fluidColor = FluidTankScreen.getFluidColor(fluid);
        RenderSystem.setShaderColor(fluidColor[0], fluidColor[1], fluidColor[2], fluidColor[3]);
        
        int drawY = fluidY + SLOT_SIZE - fluidHeight;
        for (int yOffset = 0; yOffset < fluidHeight; yOffset += 16) {
            int drawHeight = Math.min(16, fluidHeight - yOffset);
            context.drawTexture(WATER_TEXTURE, fluidX, drawY + yOffset,
                    0, 0, SLOT_SIZE, drawHeight, 16, 16);
        }

        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        if (!handler.nowFluidMode()) return;

        int bgX = (this.width - this.backgroundWidth) / 2;
        int bgY = (this.height - this.backgroundHeight) / 2;

        int fluidX = bgX + FLUID_SLOT_X;
        int fluidY = bgY + FLUID_SLOT_Y;
        if (mouseX >= fluidX && mouseX <= fluidX + SLOT_SIZE &&
                mouseY >= fluidY && mouseY <= fluidY + SLOT_SIZE) {
            int amount = handler.getFluidAmount();
            int capacity = handler.getFluidCapacity();

            List<Text> tooltip = new ArrayList<>();
            if (amount > 0) {
                tooltip.add(Text.translatable("tooltip.filling_unit.tank",
                        amount / 1000, capacity / 1000).formatted(Formatting.AQUA));
            } else {
                tooltip.add(Text.translatable("tooltip.filling_unit.tank_empty")
                        .formatted(Formatting.GRAY));
            }
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }
}
