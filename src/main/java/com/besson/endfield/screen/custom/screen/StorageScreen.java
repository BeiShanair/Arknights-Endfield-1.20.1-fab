package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.screen.custom.screenHandler.StorageScreenHandler;
import com.besson.endfield.utils.storage.StorageEntry;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
    
        net.minecraft.item.ItemStack stack = slot.getStack();
        List<net.minecraft.text.Text> tooltip = new ArrayList<>(this.getTooltipFromItem(stack));
        if (tooltip.isEmpty()) {
            tooltip.add(Text.literal("Unknown Item"));
        }
        
        tooltip.add(Text.literal("Num: ")
                .formatted(Formatting.GRAY)
                .append(Text.literal(formatExact(count)).formatted(Formatting.AQUA)));
        
        context.getMatrices().push();
        context.getMatrices().translate(0.0D, 0.0D, 600.0D);
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
        if (slot == null) {
            super.onMouseClick(slot, slotId, button, actionType);
            return;
        }

        int index = handler.slots.indexOf(slot);
        if (index < 0) {
            super.onMouseClick(slot, slotId, button, actionType);
            return;
        }

        int storageSlotCount = handler.getStorageSlotCount();
        List<StorageEntry> visible = handler.getVisibleEntries();
        
        if (index < storageSlotCount) {
            if (index >= visible.size()) {
                return;
            }

            StorageEntry entry = visible.get(index);
            Item item = entry.getItem();

            int amount;
            boolean shift = Screen.hasShiftDown() || actionType == SlotActionType.QUICK_MOVE;

            if (shift && button == 0) {
                amount = item.getMaxCount();
            } else if (button == 0) {
                amount = 1;
            } else if (button == 1) {
                int max = item.getMaxCount();
                amount = Math.max(1, (max + 1) / 2);
            } else {
                return;
            }

            long available = entry.getCount();
            int toRequest = (int) Math.min((long) amount, available);
            if (toRequest <= 0) return;

            requestItem(entry, toRequest);
            return;
        }
        
        ItemStack stack = slot.getStack();
        if (stack == null || stack.isEmpty()) {
            if (actionType == SlotActionType.QUICK_MOVE) {
                return;
            }
            
            super.onMouseClick(slot, slotId, button, actionType);
            return;
        }

        boolean shift = Screen.hasShiftDown() || actionType == SlotActionType.QUICK_MOVE;
        int amountToDeposit;

        if (shift && button == 0) {
            amountToDeposit = stack.getCount();
        } else if (button == 0) {
            amountToDeposit = 1;
        } else if (button == 1) {
            amountToDeposit = Math.max(1, (stack.getCount() + 1) / 2);
        } else {
            return;
        }

        if (amountToDeposit <= 0) return;

        amountToDeposit = Math.min(amountToDeposit, stack.getCount());

        sendDepositPacket(stack, amountToDeposit);
    }

    private void requestItem(StorageEntry entry, int amount) {

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeIdentifier(Registries.ITEM.getId(entry.getItem()));
        buf.writeNbt(entry.getTag());
        buf.writeInt(amount);

        ClientPlayNetworking.send(ModNetWorking.REQUEST_ITEM, buf);
    }

    private void sendDepositPacket(ItemStack stack, int amount) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeIdentifier(Registries.ITEM.getId(stack.getItem()));
        buf.writeNbt(stack.getNbt());
        buf.writeInt(amount);

        ClientPlayNetworking.send(ModNetWorking.DEPOSIT_ITEM, buf);
    }
}
