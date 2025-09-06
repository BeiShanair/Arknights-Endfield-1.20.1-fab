package com.besson.endfield.renderer;

import com.besson.endfield.item.custom.ProtocolAnchorCoreItem;
import com.besson.endfield.model.ProtocolAnchorCoreItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ProtocolAnchorCoreItemRenderer extends GeoItemRenderer<ProtocolAnchorCoreItem> {
    public ProtocolAnchorCoreItemRenderer() {
        super(new ProtocolAnchorCoreItemModel());
    }
}
