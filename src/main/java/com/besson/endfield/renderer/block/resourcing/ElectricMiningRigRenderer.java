package com.besson.endfield.renderer.block.resourcing;

import com.besson.endfield.blockentity.custom.resourcing.ElectricMiningRigBlockEntity;
import com.besson.endfield.model.block.resourcing.ElectricMiningRigModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ElectricMiningRigRenderer extends GeoBlockRenderer<ElectricMiningRigBlockEntity> {
    public ElectricMiningRigRenderer(BlockEntityRendererFactory.Context context) {
        super(new ElectricMiningRigModel());
    }
}
