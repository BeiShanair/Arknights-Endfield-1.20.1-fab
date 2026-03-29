package com.besson.endfield.model.item.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.combat.MarshGasMkIItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MarshGasMkIItemModel extends GeoModel<MarshGasMkIItem> {
    @Override
    public Identifier getModelResource(MarshGasMkIItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/marsh_gas_mk_i.geo.json");
    }

    @Override
    public Identifier getTextureResource(MarshGasMkIItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/marsh_gas_mk_i.png");
    }

    @Override
    public Identifier getAnimationResource(MarshGasMkIItem animatable) {
        return null;
    }
}
