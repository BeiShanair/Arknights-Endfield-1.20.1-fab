package com.besson.endfield.renderer.item.powering;

import com.besson.endfield.item.custom.powering.ProtocolAnchorCoreItem;
import com.besson.endfield.model.item.powering.ProtocolAnchorCoreItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ProtocolAnchorCoreItemRenderer extends GeoItemRenderer<ProtocolAnchorCoreItem> {
    public ProtocolAnchorCoreItemRenderer() {
        super(new ProtocolAnchorCoreItemModel());
    }
}
