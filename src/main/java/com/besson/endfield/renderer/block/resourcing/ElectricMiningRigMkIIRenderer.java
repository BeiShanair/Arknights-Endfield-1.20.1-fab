package com.besson.endfield.renderer.block.resourcing;

import com.besson.endfield.blockentity.custom.resourcing.ElectricMiningRigMkIIBlockEntity;
import com.besson.endfield.model.block.resourcing.ElectricMiningRigMkIIModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ElectricMiningRigMkIIRenderer extends GeoBlockRenderer<ElectricMiningRigMkIIBlockEntity> {
    public ElectricMiningRigMkIIRenderer(BlockEntityRendererFactory.Context context) {
        super(new ElectricMiningRigMkIIModel());
    }
}
