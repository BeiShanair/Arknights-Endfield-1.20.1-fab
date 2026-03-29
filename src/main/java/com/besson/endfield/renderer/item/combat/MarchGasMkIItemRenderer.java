package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.MarshGasMkIItem;
import com.besson.endfield.model.item.combat.MarshGasMkIItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MarchGasMkIItemRenderer extends GeoItemRenderer<MarshGasMkIItem> {
    public MarchGasMkIItemRenderer() {
        super(new MarshGasMkIItemModel());
    }
}
