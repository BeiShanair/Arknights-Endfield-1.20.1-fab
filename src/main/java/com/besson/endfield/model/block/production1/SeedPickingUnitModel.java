package com.besson.endfield.model.block.production1;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.production1.SeedPickingUnitBlock;
import com.besson.endfield.blockentity.custom.production1.SeedPickingUnitBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SeedPickingUnitModel extends GeoModel<SeedPickingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(SeedPickingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/seed_picking_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(SeedPickingUnitBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof SeedPickingUnitBlock b) {
            if (b.getDefaultState().get(SeedPickingUnitBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/seed_picking_unit_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/seed_picking_unit.png");
    }

    @Override
    public Identifier getAnimationResource(SeedPickingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/seed_picking_unit.animation.json");
    }

    @Override
    public RenderLayer getRenderType(SeedPickingUnitBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
