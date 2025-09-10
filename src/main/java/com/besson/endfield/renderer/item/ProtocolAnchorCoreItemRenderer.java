package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.ProtocolAnchorCoreItem;
import com.besson.endfield.model.item.ProtocolAnchorCoreItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ProtocolAnchorCoreItemRenderer extends GeoItemRenderer<ProtocolAnchorCoreItem> {
    public ProtocolAnchorCoreItemRenderer() {
        super(new ProtocolAnchorCoreItemModel());
    }
}
