package com.besson.endfield.renderer;

import com.besson.endfield.item.custom.PortableOriginiumRigItem;
import com.besson.endfield.model.PortableOriginiumItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PortableOriginiumRigItemRenderer extends GeoItemRenderer<PortableOriginiumRigItem> {
    public PortableOriginiumRigItemRenderer() {
        super(new PortableOriginiumItemModel());
    }
}
