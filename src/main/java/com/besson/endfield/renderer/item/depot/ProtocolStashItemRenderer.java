package com.besson.endfield.renderer.item.depot;

import com.besson.endfield.item.custom.depot.ProtocolStashItem;
import com.besson.endfield.model.item.depot.ProtocolStashItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ProtocolStashItemRenderer extends GeoItemRenderer<ProtocolStashItem> {
    public ProtocolStashItemRenderer() {
        super(new ProtocolStashItemModel());
    }
}
