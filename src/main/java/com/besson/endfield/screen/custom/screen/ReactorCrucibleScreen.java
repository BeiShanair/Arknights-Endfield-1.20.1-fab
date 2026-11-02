package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.screen.custom.screenHandler.ReactorCrucibleScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ReactorCrucibleScreen extends BaseCrucibleScreen<ReactorCrucibleScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/reactor_crucible.png");

    private static final int[] INPUT_X = {79, 47, 111, 60, 98};
    private static final int[] INPUT_Y = {31, 55, 55, 90, 90};

    public ReactorCrucibleScreen(ReactorCrucibleScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected Identifier getTexture() {
        return TEXTURE;
    }

    @Override
    protected int[] getInputX() {
        return INPUT_X;
    }

    @Override
    protected int[] getInputY() {
        return INPUT_Y;
    }
}
