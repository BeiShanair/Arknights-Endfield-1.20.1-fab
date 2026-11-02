package com.besson.endfield.renderer.item.production2;

import com.besson.endfield.item.custom.production2.ReactorCrucibleItem;
import com.besson.endfield.model.item.production2.ReactorCrucibleItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ReactorCrucibleItemRenderer extends GeoItemRenderer<ReactorCrucibleItem> {
    public ReactorCrucibleItemRenderer() {
        super(new ReactorCrucibleItemModel());
    }
}
