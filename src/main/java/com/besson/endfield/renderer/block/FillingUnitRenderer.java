package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.FillingUnitBlockEntity;
import com.besson.endfield.model.block.FillingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FillingUnitRenderer extends GeoBlockRenderer<FillingUnitBlockEntity> {
    public FillingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new FillingUnitModel());
    }
}
