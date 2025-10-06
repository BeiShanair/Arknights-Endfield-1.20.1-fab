package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.PackagingUnitBlockEntity;
import com.besson.endfield.model.block.PackagingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PackagingUnitRenderer extends GeoBlockRenderer<PackagingUnitBlockEntity> {
    public PackagingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new PackagingUnitModel());
    }
}
