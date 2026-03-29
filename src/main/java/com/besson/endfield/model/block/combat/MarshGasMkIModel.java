package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.combat.MarshGasMkIBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MarshGasMkIModel extends GeoModel<MarshGasMkIBlockEntity> {
    @Override
    public Identifier getModelResource(MarshGasMkIBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/marsh_gas_mk_i.geo.json");
    }

    @Override
    public Identifier getTextureResource(MarshGasMkIBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/marsh_gas_mk_i.png");
    }

    @Override
    public Identifier getAnimationResource(MarshGasMkIBlockEntity animatable) {
        return null;
    }
}
