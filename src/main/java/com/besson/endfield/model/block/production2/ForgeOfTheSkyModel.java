package com.besson.endfield.model.block.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production2.ForgeOfTheSkyBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ForgeOfTheSkyModel extends GeoModel<ForgeOfTheSkyBlockEntity> {
    @Override
    public Identifier getModelResource(ForgeOfTheSkyBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/forge_of_the_sky.geo.json");
    }

    @Override
    public Identifier getTextureResource(ForgeOfTheSkyBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/forge_of_the_sky.png");
    }

    @Override
    public Identifier getAnimationResource(ForgeOfTheSkyBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(ForgeOfTheSkyBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
