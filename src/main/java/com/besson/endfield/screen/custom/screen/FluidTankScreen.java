package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.FluidBottleItem;
import com.besson.endfield.item.custom.MaterialBottleItem;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.screen.custom.screenHandler.FluidTankScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

public class FluidTankScreen extends HandledScreen<FluidTankScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/fluid_pump.png");
    private static final Identifier WATER_TEXTURE = new Identifier("minecraft", "textures/block/water_still.png");

    public FluidTankScreen(FluidTankScreenHandler handler, PlayerInventory inventory, Text title) {
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
            
            Fluid fluid = Registries.FLUID.get(handler.getFluidId());
            float[] fluidColor = getFluidColor(fluid);

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
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        int fluidX = x + 64;
        int fluidY = y + 11;
        int fluidWidth = 48;
        int fluidHeight = 64;

        if (mouseX >= fluidX && mouseX <= fluidX + fluidWidth &&
                mouseY >= fluidY && mouseY <= fluidY + fluidHeight) {

            int amount = handler.getFluidAmount();
            int capacity = handler.getFluidCapacity();

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
            ItemStack cursor = client.player.currentScreenHandler.getCursorStack();
            if (cursor.getItem() instanceof FluidBottleItem || cursor.getItem() instanceof MaterialBottleItem) {
                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                buf.writeBlockPos(handler.getBlockEntityPos());
                ClientPlayNetworking.send(ModNetWorking.TANK_BOTTLE_TRANSFER, buf);
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    private static final float FLUID_ALPHA = 0.8f;
    
    static float[] getFluidColor(Fluid fluid) {
        FluidRenderHandler renderHandler = FluidRenderHandlerRegistry.INSTANCE.get(fluid);
        if (renderHandler != null) {
            int argb = renderHandler.getFluidColor(null, null, fluid.getDefaultState());
            return new float[]{
                    ((argb >>> 16) & 0xFF) / 255.0f,
                    ((argb >>> 8) & 0xFF) / 255.0f,
                    (argb & 0xFF) / 255.0f,
                    FLUID_ALPHA
            };
        }
        if (fluid.matchesType(Fluids.WATER)) {
            return new float[]{0.2f, 0.4f, 1.0f, 0.7f};
        }
        
        if (fluid.matchesType(Fluids.LAVA)) {
            return new float[]{1.0f, 0.55f, 0.1f, 0.95f};
        }
        
        return new float[]{0.2f, 0.4f, 1.0f, 0.7f};
    }
}
