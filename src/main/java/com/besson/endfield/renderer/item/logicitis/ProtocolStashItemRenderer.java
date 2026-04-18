package com.besson.endfield.renderer.item.logicitis;

import com.besson.endfield.item.custom.logicitis.ProtocolStashItem;
import com.besson.endfield.model.item.logicitis.ProtocolStashItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ProtocolStashItemRenderer extends GeoItemRenderer<ProtocolStashItem> {
    public ProtocolStashItemRenderer() {
        super(new ProtocolStashItemModel());
    }
}
