package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.custom.CrafterRecipe;
import com.besson.endfield.screen.custom.screenHandler.CrafterScreenHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class CrafterScreen extends HandledScreen<CrafterScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/crafter.png");
    private static final int RECIPE_LIST_COLUMNS = 2;
    private static final int RECIPE_LIST_ROWS = 3;
    private static final int RECIPE_ENTRY_WIDTH = 16;
    private static final int RECIPE_ENTRY_HEIGHT = 18;
    private static final int VISIBLE_RECIPE_COUNT = RECIPE_LIST_COLUMNS * RECIPE_LIST_ROWS;
    private static final int RECIPE_LIST_OFFSET_X = 76;
    private static final int RECIPE_LIST_OFFSET_Y = 14;
    private static final int SCROLLBAR_X = 109;
    private static final int SCROLLBAR_Y = 15;
    private static final int SCROLLBAR_WIDTH = 4;
    private static final int SCROLLBAR_HEIGHT = 54;
    private static final int SCROLLBAR_THUMB_HEIGHT = 15;
    private static final int PANEL_COLOR = 0xFF8A8A8A;
    private static final int PANEL_BORDER_COLOR = 0xFF4E4E4E;
    private static final int RECIPE_NORMAL_COLOR = 0xFF6D6D6D;
    private static final int RECIPE_HOVER_COLOR = 0xFF9A9A9A;
    private static final int RECIPE_SELECTED_COLOR = 0xFFC8C8C8;
    private static final int RECIPE_BORDER_COLOR = 0xFF3F3F3F;

    private float scrollAmount;
    private boolean mouseClicked;
    private int scrollOffset;
    private boolean canCraft;
    
    public CrafterScreen(CrafterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        handler.setContentsChangedListener(this::onInventoryChange);
        this.canCraft = handler.canCraft();
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderRecipeList(context, mouseX, mouseY, x + RECIPE_LIST_OFFSET_X, y + RECIPE_LIST_OFFSET_Y);
        renderScrollbar(context, x, y);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);
    }

    @Override
    protected void drawMouseoverTooltip(DrawContext context, int x, int y) {
        super.drawMouseoverTooltip(context, x, y);
        if (!this.canCraft) {
            return;
        }

        int recipeX = this.x + RECIPE_LIST_OFFSET_X;
        int recipeY = this.y + RECIPE_LIST_OFFSET_Y;
        List<CrafterRecipe> recipes = this.handler.getCurrentRecipes();

        for (int i = this.scrollOffset; i < this.scrollOffset + VISIBLE_RECIPE_COUNT && i < this.handler.getAvailableRecipeCount(); i++) {
            int visibleIndex = i - this.scrollOffset;
            int iconX = recipeX + visibleIndex % RECIPE_LIST_COLUMNS * RECIPE_ENTRY_WIDTH;
            int iconY = recipeY + visibleIndex / RECIPE_LIST_COLUMNS * RECIPE_ENTRY_HEIGHT + 1;
            if (x >= iconX && x < iconX + RECIPE_ENTRY_WIDTH && y >= iconY && y < iconY + RECIPE_ENTRY_HEIGHT) {
                context.drawItemTooltip(this.textRenderer, recipes.get(i).getOutput(this.client.world.getRegistryManager()), x, y);
            }
        }
    }

    private void renderRecipeList(DrawContext context, int mouseX, int mouseY, int x, int y) {
        context.fill(x - 2, y - 1, x + RECIPE_LIST_COLUMNS * RECIPE_ENTRY_WIDTH + 2, y + RECIPE_LIST_ROWS * RECIPE_ENTRY_HEIGHT + 2, PANEL_COLOR);
        context.drawBorder(x - 2, y - 1, RECIPE_LIST_COLUMNS * RECIPE_ENTRY_WIDTH + 4, RECIPE_LIST_ROWS * RECIPE_ENTRY_HEIGHT + 3, PANEL_BORDER_COLOR);

        if (!this.canCraft) {
            return;
        }

        List<CrafterRecipe> recipes = this.handler.getCurrentRecipes();
        for (int i = this.scrollOffset; i < this.scrollOffset + VISIBLE_RECIPE_COUNT && i < this.handler.getAvailableRecipeCount(); i++) {
            int visibleIndex = i - this.scrollOffset;
            int iconX = x + visibleIndex % RECIPE_LIST_COLUMNS * RECIPE_ENTRY_WIDTH;
            int iconY = y + visibleIndex / RECIPE_LIST_COLUMNS * RECIPE_ENTRY_HEIGHT + 1;
            boolean hovered = mouseX >= iconX && mouseX < iconX + RECIPE_ENTRY_WIDTH && mouseY >= iconY && mouseY < iconY + RECIPE_ENTRY_HEIGHT;
            int color = i == this.handler.getSelectedRecipeIndex() ? RECIPE_SELECTED_COLOR : hovered ? RECIPE_HOVER_COLOR : RECIPE_NORMAL_COLOR;

            context.fill(iconX, iconY, iconX + RECIPE_ENTRY_WIDTH, iconY + RECIPE_ENTRY_HEIGHT, color);
            context.drawBorder(iconX, iconY, RECIPE_ENTRY_WIDTH, RECIPE_ENTRY_HEIGHT, RECIPE_BORDER_COLOR);
            context.drawItem(recipes.get(i).getOutput(this.client.world.getRegistryManager()), iconX, iconY + 1);
        }
    }

    private void renderScrollbar(DrawContext context, int x, int y) {
        if (!this.shouldScroll()) {
            return;
        }

        int trackX = x + SCROLLBAR_X;
        int trackY = y + SCROLLBAR_Y;
        int thumbY = trackY + (int)((SCROLLBAR_HEIGHT - SCROLLBAR_THUMB_HEIGHT) * this.scrollAmount);

        context.fill(trackX, trackY, trackX + SCROLLBAR_WIDTH, trackY + SCROLLBAR_HEIGHT, 0xFF5A5A5A);
        context.fill(trackX, thumbY, trackX + SCROLLBAR_WIDTH, thumbY + SCROLLBAR_THUMB_HEIGHT, 0xFFD6D6D6);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.mouseClicked = false;
        if (this.canCraft) {
            int recipeX = this.x + RECIPE_LIST_OFFSET_X;
            int recipeY = this.y + RECIPE_LIST_OFFSET_Y;

            for (int i = this.scrollOffset; i < this.scrollOffset + VISIBLE_RECIPE_COUNT && i < this.handler.getAvailableRecipeCount(); i++) {
                int visibleIndex = i - this.scrollOffset;
                double relativeX = mouseX - (recipeX + visibleIndex % RECIPE_LIST_COLUMNS * RECIPE_ENTRY_WIDTH);
                double relativeY = mouseY - (recipeY + visibleIndex / RECIPE_LIST_COLUMNS * RECIPE_ENTRY_HEIGHT + 1);

                if (relativeX >= 0.0 && relativeY >= 0.0 && relativeX < RECIPE_ENTRY_WIDTH && relativeY < RECIPE_ENTRY_HEIGHT && this.handler.onButtonClick(this.client.player, i)) {
                    MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.client.interactionManager.clickButton(this.handler.syncId, i);
                    return true;
                }
            }

            int scrollbarX = this.x + SCROLLBAR_X;
            int scrollbarY = this.y + SCROLLBAR_Y;
            if (mouseX >= scrollbarX && mouseX < scrollbarX + SCROLLBAR_WIDTH && mouseY >= scrollbarY && mouseY < scrollbarY + SCROLLBAR_HEIGHT) {
                this.mouseClicked = true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.mouseClicked && this.shouldScroll()) {
            int top = this.y + SCROLLBAR_Y;
            int bottom = top + SCROLLBAR_HEIGHT;
            this.scrollAmount = ((float)mouseY - top - SCROLLBAR_THUMB_HEIGHT / 2.0F) / (bottom - top - SCROLLBAR_THUMB_HEIGHT);
            this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
            this.scrollOffset = (int)(this.scrollAmount * this.getMaxScroll() + 0.5F) * RECIPE_LIST_COLUMNS;
            return true;
        }

        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (this.shouldScroll()) {
            int maxScroll = this.getMaxScroll();
            this.scrollAmount = MathHelper.clamp(this.scrollAmount - (float)amount / maxScroll, 0.0F, 1.0F);
            this.scrollOffset = (int)(this.scrollAmount * maxScroll + 0.5F) * RECIPE_LIST_COLUMNS;
        }

        return true;
    }

    private boolean shouldScroll() {
        return this.canCraft && this.handler.getAvailableRecipeCount() > VISIBLE_RECIPE_COUNT;
    }

    private int getMaxScroll() {
        return (this.handler.getAvailableRecipeCount() + RECIPE_LIST_COLUMNS - 1) / RECIPE_LIST_COLUMNS - RECIPE_LIST_ROWS;
    }

    private void onInventoryChange() {
        this.canCraft = this.handler.canCraft();
        if (!this.canCraft) {
            this.scrollAmount = 0.0F;
            this.scrollOffset = 0;
        }
    }
}
