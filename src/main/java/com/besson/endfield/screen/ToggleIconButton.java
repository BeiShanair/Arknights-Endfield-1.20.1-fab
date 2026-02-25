package com.besson.endfield.screen;

import com.besson.endfield.ArknightsEndfield;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class ToggleIconButton extends PressableWidget {
    private static final Identifier SWITCH_ENABLE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/button/switch_enable.png");
    private static final Identifier SWITCH_DISABLE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/button/switch_disable.png");

    private final BooleanSupplier booleanSupplier;
    private final Consumer<Boolean> onToggle;

    public ToggleIconButton(int i, int j, BooleanSupplier booleanSupplier, Consumer<Boolean> onToggle) {
        super(i, j, 16, 16, Text.empty());
        this.booleanSupplier = booleanSupplier;
        this.onToggle = onToggle;
    }

    @Override
    public void onPress() {
        boolean newState = !booleanSupplier.getAsBoolean();
        onToggle.accept(newState);
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        Identifier texture = booleanSupplier.getAsBoolean() ? SWITCH_ENABLE : SWITCH_DISABLE;
        context.drawTexture(texture, getX(), getY(), 0, 0, this.width, this.height, 16, 16);

        if (isHovered()) {
            context.fill(getX(), getY(), getX() + this.width, getY() + this.height, 0x40FFFFFF);
        }
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }
}
