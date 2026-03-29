package com.besson.endfield.renderer.block.powering;

import com.besson.endfield.blockentity.custom.powering.ThermalBankBlockEntity;
import com.besson.endfield.model.block.powering.ThermalBankModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ThermalBankRenderer extends GeoBlockRenderer<ThermalBankBlockEntity> {
    public ThermalBankRenderer(BlockEntityRendererFactory.Context context) {
        super(new ThermalBankModel());
    }
}
