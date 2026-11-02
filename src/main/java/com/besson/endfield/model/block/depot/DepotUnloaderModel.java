package com.besson.endfield.model.block.depot;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.depot.DepotUnloaderBlock;
import com.besson.endfield.blockentity.custom.depot.DepotUnloaderBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DepotUnloaderModel extends GeoModel<DepotUnloaderBlockEntity> {
    @Override
    public Identifier getModelResource(DepotUnloaderBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/depot_unloader.geo.json");
    }

    @Override
    public Identifier getTextureResource(DepotUnloaderBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof DepotUnloaderBlock b) {
            if (b.getDefaultState().get(DepotUnloaderBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_unloader_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_unloader.png");
    }

    @Override
    public Identifier getAnimationResource(DepotUnloaderBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(DepotUnloaderBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
