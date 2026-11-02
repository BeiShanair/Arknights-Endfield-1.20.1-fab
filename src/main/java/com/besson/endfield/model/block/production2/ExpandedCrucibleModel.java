package com.besson.endfield.model.block.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production2.ExpandedCrucibleBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ExpandedCrucibleModel extends GeoModel<ExpandedCrucibleBlockEntity> {
    @Override
    public Identifier getModelResource(ExpandedCrucibleBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/expanded_crucible.geo.json");
    }

    @Override
    public Identifier getTextureResource(ExpandedCrucibleBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/expanded_crucible.png");
    }

    @Override
    public Identifier getAnimationResource(ExpandedCrucibleBlockEntity animatable) {
        return null;
    }
}
