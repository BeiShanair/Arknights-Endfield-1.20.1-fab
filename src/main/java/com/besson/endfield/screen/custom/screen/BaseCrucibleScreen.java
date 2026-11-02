package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.blockentity.custom.production2.BaseCrucibleBlockEntity;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.screen.custom.screenHandler.BaseCrucibleScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCrucibleScreen<H extends BaseCrucibleScreenHandler> extends HandledScreen<H> {
    private static final Identifier WATER_TEXTURE = new Identifier("minecraft", "textures/block/water_still.png");

    private static final Text ITEM_OUTPUT_LABEL = Text.translatable("tooltip.crucible.item_output");
    private static final Text FLUID_OUTPUT_LABEL = Text.translatable("tooltip.crucible.fluid_output");
    private static final Text UNSET_LABEL = Text.translatable("tooltip.crucible.unset");
    private static final Text CLICK_CLEAR_LABEL = Text.translatable("tooltip.crucible.click_clear");

    protected static final int ITEM_OUTPUT_X = 79;
    protected static final int FLUID_OUTPUT_X_0 = 47;
    protected static final int FLUID_OUTPUT_X_1 = 111;
    protected static final int OUTPUT_Y = 127;
    protected static final int SLOT_SIZE = 16;

    protected BaseCrucibleScreen(H handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
        this.playerInventoryTitleY = 1000;
    }

    protected abstract Identifier getTexture();

    protected abstract int[] getInputX();

    protected abstract int[] getInputY();

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        Identifier texture = getTexture();
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, texture);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(texture, x, y, 0, 0, backgroundWidth, backgroundHeight);

        int progress = handler.getBlockEntity().getProgress();
        int maxProgress = handler.getBlockEntity().getMaxProgress();
        int progressWidth = maxProgress <= 0 ? 0 : (int) (48 * (progress / (float) maxProgress));
        context.fill(x + 64, y + 110, x + 112, y + 114, 0xFF14171B);
        context.fill(x + 64, y + 110, x + 64 + progressWidth, y + 114, 0xFF72D0B2);

        drawResources(context, x, y);
    }

    private void drawResources(DrawContext context, int x, int y) {
        List<BaseCrucibleBlockEntity.StoredResource> resources = handler.getBlockEntity().getDisplayResources();
        int[] inputX = getInputX();
        int[] inputY = getInputY();
        for (int i = 0; i < resources.size() && i < inputX.length; i++) {
            BaseCrucibleBlockEntity.StoredResource resource = resources.get(i);
            int slotX = x + inputX[i] + 1;
            int slotY = y + inputY[i] + 1;
            if (resource.item()) {
                ItemStack stack = resource.asStack();
                context.drawItem(stack, slotX, slotY);
                context.drawItemInSlot(textRenderer, stack, slotX, slotY, formatAmount(resource.amount()));
            } else {
                drawInputFluidCell(context, slotX, slotY, resource.fluidVariant(), resource.amount());
            }
        }

        if (!handler.getBlockEntity().getItemOutputFilter().isBlank()) {
            ItemStack stack = handler.getBlockEntity().getItemOutputFilter().toStack();
            context.drawItem(stack, x + ITEM_OUTPUT_X + 1, y + OUTPUT_Y + 1);
        }
        drawFluidOutput(context, x + FLUID_OUTPUT_X_0 + 1, y + OUTPUT_Y + 1, 0);
        drawFluidOutput(context, x + FLUID_OUTPUT_X_1 + 1, y + OUTPUT_Y + 1, 1);
    }

    private void drawFluidOutput(DrawContext context, int x, int y, int index) {
        FluidVariant filter = handler.getBlockEntity().getFluidOutputFilter(index);
        if (!filter.isBlank()) {
            drawFluidCell(context, x, y, filter);
        }
    }

    private void drawInputFluidCell(DrawContext context, int x, int y, FluidVariant variant, long amount) {
        long capacity = BaseCrucibleBlockEntity.MAX_FLUID_AMOUNT;
        int fluidHeight = capacity <= 0 ? 0 : (int) (amount / (float) capacity * SLOT_SIZE);
        if (fluidHeight <= 0) return;
        drawFluidTexture(context, x, y + SLOT_SIZE - fluidHeight, SLOT_SIZE, fluidHeight, variant.getFluid());
    }

    private void drawFluidCell(DrawContext context, int x, int y, FluidVariant variant) {
        drawFluidTexture(context, x, y, SLOT_SIZE, SLOT_SIZE, variant.getFluid());
    }
    
    private void drawFluidTexture(DrawContext context, int x, int y, int width, int height, Fluid fluid) {
        float[] fluidColor = FluidTankScreen.getFluidColor(fluid);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, WATER_TEXTURE);
        RenderSystem.setShaderColor(fluidColor[0], fluidColor[1], fluidColor[2], fluidColor[3]);
        context.drawTexture(WATER_TEXTURE, x, y, 0, 0, width, height, 16, 16);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.disableBlend();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int x = (width - backgroundWidth) / 2;
            int y = (height - backgroundHeight) / 2;
            int[] inputX = getInputX();
            int[] inputY = getInputY();
            for (int i = 0; i < inputX.length; i++) {
                if (isIn(mouseX, mouseY, x + inputX[i], y + inputY[i], SLOT_SIZE, SLOT_SIZE)) {
                    PacketByteBuf buf = PacketByteBufs.create();
                    buf.writeBlockPos(handler.getBlockEntity().getPos());
                    buf.writeInt(i);
                    ClientPlayNetworking.send(ModNetWorking.CRUCIBLE_SET_OUTPUT, buf);
                    return true;
                }
            }
            
            if (isIn(mouseX, mouseY, x + ITEM_OUTPUT_X, y + OUTPUT_Y, SLOT_SIZE, SLOT_SIZE)
                    && !handler.getBlockEntity().getItemOutputFilter().isBlank()) {
                sendClearOutput(0);
                return true;
            }
            if (isIn(mouseX, mouseY, x + FLUID_OUTPUT_X_0, y + OUTPUT_Y, SLOT_SIZE, SLOT_SIZE)
                    && !handler.getBlockEntity().getFluidOutputFilter(0).isBlank()) {
                sendClearOutput(1);
                return true;
            }
            if (isIn(mouseX, mouseY, x + FLUID_OUTPUT_X_1, y + OUTPUT_Y, SLOT_SIZE, SLOT_SIZE)
                    && !handler.getBlockEntity().getFluidOutputFilter(1).isBlank()) {
                sendClearOutput(2);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void sendClearOutput(int index) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(handler.getBlockEntity().getPos());
        buf.writeInt(index);
        ClientPlayNetworking.send(ModNetWorking.CRUCIBLE_CLEAR_OUTPUT, buf);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
        drawCustomTooltips(context, mouseX, mouseY);
    }

    private void drawCustomTooltips(DrawContext context, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        List<BaseCrucibleBlockEntity.StoredResource> resources = handler.getBlockEntity().getDisplayResources();
        int[] inputX = getInputX();
        int[] inputY = getInputY();
        for (int i = 0; i < resources.size() && i < inputX.length; i++) {
            if (isIn(mouseX, mouseY, x + inputX[i], y + inputY[i], SLOT_SIZE, SLOT_SIZE)) {
                context.drawTooltip(textRenderer, getTooltip(resources.get(i)), mouseX, mouseY);
                return;
            }
        }

        if (isIn(mouseX, mouseY, x + ITEM_OUTPUT_X, y + OUTPUT_Y, SLOT_SIZE, SLOT_SIZE)) {
            context.drawTooltip(textRenderer, itemOutputTooltip(handler.getBlockEntity().getItemOutputFilter()), mouseX, mouseY);
            return;
        }
        if (isIn(mouseX, mouseY, x + FLUID_OUTPUT_X_0, y + OUTPUT_Y, SLOT_SIZE, SLOT_SIZE)) {
            context.drawTooltip(textRenderer, fluidOutputTooltip(handler.getBlockEntity().getFluidOutputFilter(0)), mouseX, mouseY);
            return;
        }
        if (isIn(mouseX, mouseY, x + FLUID_OUTPUT_X_1, y + OUTPUT_Y, SLOT_SIZE, SLOT_SIZE)) {
            context.drawTooltip(textRenderer, fluidOutputTooltip(handler.getBlockEntity().getFluidOutputFilter(1)), mouseX, mouseY);
        }
    }

    private List<Text> getTooltip(BaseCrucibleBlockEntity.StoredResource resource) {
        List<Text> tooltip = new ArrayList<>();
        if (resource.item()) {
            tooltip.add(resource.itemVariant().toStack().getName());
            tooltip.add(Text.literal(String.valueOf(resource.amount())).formatted(Formatting.GRAY));
        } else {
            Fluid fluid = resource.fluidVariant().getFluid();
            Text fluidName = Text.translatable(fluid.getDefaultState().getBlockState().getBlock().getTranslationKey());
            tooltip.add(fluidName);
            tooltip.add(Text.literal(resource.amount() + " mB").formatted(Formatting.GRAY));
        }
        return tooltip;
    }
    
    private List<Text> itemOutputTooltip(ItemVariant filter) {
        List<Text> tooltip = new ArrayList<>();
        if (filter.isBlank()) {
            tooltip.add(ITEM_OUTPUT_LABEL.copy().formatted(Formatting.YELLOW));
            tooltip.add(UNSET_LABEL.copy().formatted(Formatting.GRAY));
        } else {
            tooltip.add(filter.toStack().getName());
            tooltip.add(ITEM_OUTPUT_LABEL.copy().formatted(Formatting.YELLOW));
            tooltip.add(CLICK_CLEAR_LABEL.copy().formatted(Formatting.GRAY, Formatting.ITALIC));
        }
        return tooltip;
    }
    
    private List<Text> fluidOutputTooltip(FluidVariant filter) {
        List<Text> tooltip = new ArrayList<>();
        if (filter.isBlank()) {
            tooltip.add(FLUID_OUTPUT_LABEL.copy().formatted(Formatting.YELLOW));
            tooltip.add(UNSET_LABEL.copy().formatted(Formatting.GRAY));
        } else {
            Fluid fluid = filter.getFluid();
            tooltip.add(Text.translatable(fluid.getDefaultState().getBlockState().getBlock().getTranslationKey()));
            tooltip.add(FLUID_OUTPUT_LABEL.copy().formatted(Formatting.YELLOW));
            tooltip.add(CLICK_CLEAR_LABEL.copy().formatted(Formatting.GRAY, Formatting.ITALIC));
        }
        return tooltip;
    }

    private boolean isIn(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    private String formatAmount(long amount) {
        return amount > 999 ? amount / 1000 + "k" : String.valueOf(amount);
    }
}
