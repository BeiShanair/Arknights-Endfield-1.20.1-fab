package com.besson.endfield.model.block.production1;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.production1.RefiningUnitBlock;
import com.besson.endfield.blockentity.custom.production1.RefiningUnitBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RefiningUnitModel extends GeoModel<RefiningUnitBlockEntity> {
    @Override
    public Identifier getModelResource(RefiningUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/refining_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(RefiningUnitBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof RefiningUnitBlock b) {
            if (b.getDefaultState().get(RefiningUnitBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/refining_unit_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/refining_unit.png");
    }

    @Override
    public Identifier getAnimationResource(RefiningUnitBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(RefiningUnitBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
