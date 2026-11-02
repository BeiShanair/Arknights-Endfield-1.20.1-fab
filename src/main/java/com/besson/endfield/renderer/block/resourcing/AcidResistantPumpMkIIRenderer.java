package com.besson.endfield.renderer.block.resourcing;

import com.besson.endfield.blockentity.custom.resourcing.AcidResistantPumpMkIIBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.resourcing.AcidResistantPumpMkIIModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AcidResistantPumpMkIIRenderer extends GeoBlockRenderer<AcidResistantPumpMkIIBlockEntity> {
    public AcidResistantPumpMkIIRenderer(BlockEntityRendererFactory.Context context) {
        super(new AcidResistantPumpMkIIModel());
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
