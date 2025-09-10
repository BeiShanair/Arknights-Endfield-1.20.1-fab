package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.PortableOriginiumRigItem;
import com.besson.endfield.model.item.PortableOriginiumItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PortableOriginiumRigItemRenderer extends GeoItemRenderer<PortableOriginiumRigItem> {
    public PortableOriginiumRigItemRenderer() {
        super(new PortableOriginiumItemModel());
    }
}
