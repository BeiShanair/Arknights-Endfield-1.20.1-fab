package com.besson.endfield.renderer.item.powering;

import com.besson.endfield.item.custom.powering.XiraniteRelayItem;
import com.besson.endfield.model.item.powering.XiraniteRelayItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class XiraniteRelayItemRenderer extends GeoItemRenderer<XiraniteRelayItem> {
    public XiraniteRelayItemRenderer() {
        super(new XiraniteRelayItemModel());
    }
}
