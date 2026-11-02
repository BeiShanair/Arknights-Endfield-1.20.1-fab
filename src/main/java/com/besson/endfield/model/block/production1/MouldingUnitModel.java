package com.besson.endfield.model.block.production1;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.production1.MouldingUnitBlock;
import com.besson.endfield.blockentity.custom.production1.MouldingUnitBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MouldingUnitModel extends GeoModel<MouldingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(MouldingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/moulding_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(MouldingUnitBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof MouldingUnitBlock b) {
            if (b.getDefaultState().get(MouldingUnitBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/moulding_unit_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/moulding_unit.png");
    }

    @Override
    public Identifier getAnimationResource(MouldingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/moulding_unit.animation.json");
    }

    @Override
    public RenderLayer getRenderType(MouldingUnitBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
