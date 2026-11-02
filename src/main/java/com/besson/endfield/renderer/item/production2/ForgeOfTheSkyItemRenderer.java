package com.besson.endfield.renderer.item.production2;

import com.besson.endfield.item.custom.production2.ForgeOfTheSkyItem;
import com.besson.endfield.model.item.production2.ForgeOfTheSkyItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ForgeOfTheSkyItemRenderer extends GeoItemRenderer<ForgeOfTheSkyItem> {
    public ForgeOfTheSkyItemRenderer() {
        super(new ForgeOfTheSkyItemModel());
    }
}
