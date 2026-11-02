package com.besson.endfield.renderer.item.powering;

import com.besson.endfield.item.custom.powering.XiranitePylonItem;
import com.besson.endfield.model.item.powering.XiranitePylonItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class XiranitePylonItemRenderer extends GeoItemRenderer<XiranitePylonItem> {

    public XiranitePylonItemRenderer() {
        super(new XiranitePylonItemModel());
    }
}
