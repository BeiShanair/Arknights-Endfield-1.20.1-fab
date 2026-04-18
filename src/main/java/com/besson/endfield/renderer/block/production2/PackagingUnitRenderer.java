package com.besson.endfield.renderer.block.production2;

import com.besson.endfield.blockentity.custom.production2.PackagingUnitBlockEntity;
import com.besson.endfield.model.block.production2.PackagingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PackagingUnitRenderer extends GeoBlockRenderer<PackagingUnitBlockEntity> {
    public PackagingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new PackagingUnitModel());
    }
}
