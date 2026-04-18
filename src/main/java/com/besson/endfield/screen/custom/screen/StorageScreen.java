package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.screen.custom.screenHandler.StorageScreenHandler;
import com.besson.endfield.utils.storage.StorageEntry;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class StorageScreen extends HandledScreen<StorageScreenHandler> {
    public static final Identifier TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/generic_54.png");
    private boolean tooltipAlreadyDrawn = false;
    public StorageScreen(StorageScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 222;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        tooltipAlreadyDrawn = false;
    
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawStorageAmounts(context);
        
        if (!tooltipAlreadyDrawn) {
            drawMouseoverTooltip(context, mouseX, mouseY);
        }
    }
    @Override
    protected void drawMouseoverTooltip(DrawContext context, int mouseX, int mouseY) {
        tooltipAlreadyDrawn = true;
    
        Slot slot = this.focusedSlot;
        if (slot == null) {
            super.drawMouseoverTooltip(context, mouseX, mouseY);
            return;
        }
    
        int index = handler.slots.indexOf(slot);
        if (index < 0) {
            super.drawMouseoverTooltip(context, mouseX, mouseY);
            return;
        }
    
        int storageSlotCount = handler.getStorageSlotCount();
        if (index >= storageSlotCount) {
            super.drawMouseoverTooltip(context, mouseX, mouseY);
            return;
        }
        
        List<StorageEntry> visible = handler.getVisibleEntries();
        if (index >= visible.size()) {
            super.drawMouseoverTooltip(context, mouseX, mouseY);
            return;
        }
    
        StorageEntry entry = visible.get(index);
        long count = entry.getCount();
    
        List<net.minecraft.text.Text> tooltip = new ArrayList<>();
        net.minecraft.item.ItemStack stack = slot.getStack();
        if (!stack.isEmpty()) {
            tooltip.add(stack.getName());
        } else {
            tooltip.add(Text.literal("Unknown Item"));
        }
        
        tooltip.add(Text.literal("Num: ")
                .formatted(Formatting.GRAY)
                .append(Text.literal(formatExact(count)).formatted(Formatting.AQUA)));
        
        context.getMatrices().push();
        context.getMatrices().translate(0.0D, 0.0D, 600.0D); // tooltip 再往上，避免被其他 UI 遮挡
        RenderSystem.disableDepthTest();
        context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        RenderSystem.enableDepthTest();
        context.getMatrices().pop();
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - 222) / 2;
        
        RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, 125);
        context.drawTexture(TEXTURE, x, y + 125, 0, 126, this.backgroundWidth, 96);
    }
    
    private void drawStorageAmounts(DrawContext context) {
        List<StorageEntry> visible = handler.getVisibleEntries();
        int storageSlotCount = handler.getStorageSlotCount();
        int countToDraw = Math.min(visible.size(), storageSlotCount);
        
        context.getMatrices().push();
        context.getMatrices().translate(0.0D, 0.0D, 300.0D);
        RenderSystem.disableDepthTest();
    
        for (int i = 0; i < countToDraw; i++) {
            Slot slot = handler.slots.get(i);
            StorageEntry entry = visible.get(i);
    
            long count = entry.getCount();
            String text = format(count);

            int x = this.x + slot.x + 16;
            int y = this.y + slot.y + 16;

            context.getMatrices().push();
            context.getMatrices().translate(x, y, 0.0D);
            context.getMatrices().scale(0.75f, 0.75f, 1.0f);
            context.drawText(textRenderer, text, -textRenderer.getWidth(text), -8, 0xFFFFFF, true);
            context.getMatrices().pop();
        }
        
        RenderSystem.enableDepthTest();
        context.getMatrices().pop();
    }

    private String format(long count) {
        if (count >= 1_000_000) {
            double value = count / 1_000_000.0;
            return formatDecimal(value) + "M";
        }
        if (count >= 1_000) {
            double value = count / 1_000.0;
            return formatDecimal(value) + "k";
        }
        return String.valueOf(count);
    }
    
    private String formatDecimal(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.format("%.1f", value).replaceAll("\\.?0*$", "");
        }
    }
    
    private String formatExact(long count) {
        return String.format("%,d", count);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        int maxOffset = Math.max(0, handler.getEntries().size() - handler.getStorageSlotCount());
        handler.scrollOffset = MathHelper.clamp(handler.scrollOffset - (int)amount * 9, 0, maxOffset);
        handler.refreshSlots();
        return true;
    }

    @Override
    protected void onMouseClick(Slot slot, int slotId, int button, SlotActionType actionType) {
        if (slot != null) {
            int index = handler.slots.indexOf(slot);
            if (index >= handler.getVisibleEntries().size()) return;
            
            StorageEntry entry = handler.getVisibleEntries().get(index);
            requestItem(entry.getItem(), button == 1 ? 64 : 1);
        }
    }

    private void requestItem(Item item, int amount) {

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeIdentifier(Registries.ITEM.getId(item));
        buf.writeInt(amount);

        ClientPlayNetworking.send(ModNetWorking.REQUEST_ITEM, buf);
    }
}
