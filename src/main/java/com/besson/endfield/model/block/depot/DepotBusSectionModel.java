package com.besson.endfield.model.block.depot;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.depot.DepotBusSectionBlock;
import com.besson.endfield.blockentity.custom.depot.DepotBusSectionBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DepotBusSectionModel extends GeoModel<DepotBusSectionBlockEntity> {
    @Override
    public Identifier getModelResource(DepotBusSectionBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/depot_bus_section.geo.json");
    }

    @Override
    public Identifier getTextureResource(DepotBusSectionBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof DepotBusSectionBlock b) {
            if (b.getDefaultState().get(DepotBusSectionBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_bus_section_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_bus_section.png");
    }

    @Override
    public Identifier getAnimationResource(DepotBusSectionBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(DepotBusSectionBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
