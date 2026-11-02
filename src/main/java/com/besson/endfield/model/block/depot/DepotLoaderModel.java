package com.besson.endfield.model.block.depot;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.depot.DepotLoaderBlock;
import com.besson.endfield.blockentity.custom.depot.DepotLoaderBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DepotLoaderModel extends GeoModel<DepotLoaderBlockEntity> {
    @Override
    public Identifier getModelResource(DepotLoaderBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/depot_loader.geo.json");
    }

    @Override
    public Identifier getTextureResource(DepotLoaderBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof DepotLoaderBlock b) {
            if (b.getDefaultState().get(DepotLoaderBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_loader_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_loader.png");
    }

    @Override
    public Identifier getAnimationResource(DepotLoaderBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(DepotLoaderBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
