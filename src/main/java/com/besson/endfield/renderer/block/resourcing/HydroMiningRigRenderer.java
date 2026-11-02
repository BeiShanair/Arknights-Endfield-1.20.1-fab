package com.besson.endfield.renderer.block.resourcing;

import com.besson.endfield.blockentity.custom.resourcing.HydroMiningRigBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.resourcing.HydroMiningRigModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class HydroMiningRigRenderer extends GeoBlockRenderer<HydroMiningRigBlockEntity> {
    public HydroMiningRigRenderer(BlockEntityRendererFactory.Context context) {
        super(new HydroMiningRigModel());
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
