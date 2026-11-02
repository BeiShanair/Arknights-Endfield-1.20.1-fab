package com.besson.endfield.screen.custom.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.screen.custom.screenHandler.ExpandedCrucibleScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ExpandedCrucibleScreen extends BaseCrucibleScreen<ExpandedCrucibleScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(ArknightsEndfield.MOD_ID, "textures/gui/expanded_crucible.png");

    // 8 输入槽：顶部 2、上排两侧 2、中排两侧 2、底部 2
    private static final int[] INPUT_X = {63, 95, 39, 119, 39, 119, 63, 95};
    private static final int[] INPUT_Y = {26, 26, 44, 44, 72, 72, 90, 90};

    public ExpandedCrucibleScreen(ExpandedCrucibleScreenHandler handler, PlayerInventory inventory, Text title) {
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
