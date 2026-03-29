package com.besson.endfield.renderer.item.resourcing;

import com.besson.endfield.item.custom.resourcing.PortableOriginiumRigItem;
import com.besson.endfield.model.item.resourcing.PortableOriginiumItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PortableOriginiumRigItemRenderer extends GeoItemRenderer<PortableOriginiumRigItem> {
    public PortableOriginiumRigItemRenderer() {
        super(new PortableOriginiumItemModel());
    }
}
