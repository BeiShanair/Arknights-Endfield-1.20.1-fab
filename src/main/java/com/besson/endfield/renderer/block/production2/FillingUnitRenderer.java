package com.besson.endfield.renderer.block.production2;

import com.besson.endfield.blockentity.custom.production2.FillingUnitBlockEntity;
import com.besson.endfield.model.block.production2.FillingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FillingUnitRenderer extends GeoBlockRenderer<FillingUnitBlockEntity> {
    public FillingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new FillingUnitModel());
    }
}
