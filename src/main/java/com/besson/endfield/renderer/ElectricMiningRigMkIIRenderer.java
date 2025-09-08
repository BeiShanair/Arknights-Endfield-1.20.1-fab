package com.besson.endfield.renderer;

import com.besson.endfield.blockentity.custom.ElectricMiningRigBlockEntity;
import com.besson.endfield.blockentity.custom.ElectricMiningRigMkIIBlockEntity;
import com.besson.endfield.model.ElectricMiningRigMkIIModel;
import com.besson.endfield.model.ElectricMiningRigModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ElectricMiningRigMkIIRenderer extends GeoBlockRenderer<ElectricMiningRigMkIIBlockEntity> {
    public ElectricMiningRigMkIIRenderer(BlockEntityRendererFactory.Context context) {
        super(new ElectricMiningRigMkIIModel());
    }
}
