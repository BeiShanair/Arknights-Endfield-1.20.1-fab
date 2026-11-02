package com.besson.endfield.model.block.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.production2.GrindingUnitBlock;
import com.besson.endfield.blockentity.custom.production2.GrindingUnitBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GrindingUnitModel extends GeoModel<GrindingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(GrindingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/grinding_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrindingUnitBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof GrindingUnitBlock b) {
            if (b.getDefaultState().get(GrindingUnitBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/grinding_unit_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/grinding_unit.png");
    }

    @Override
    public Identifier getAnimationResource(GrindingUnitBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(GrindingUnitBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
