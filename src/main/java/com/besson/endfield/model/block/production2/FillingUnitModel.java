package com.besson.endfield.model.block.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.production2.FillingUnitBlock;
import com.besson.endfield.blockentity.custom.production2.FillingUnitBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FillingUnitModel extends GeoModel<FillingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(FillingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/filling_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(FillingUnitBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof FillingUnitBlock b) {
            if (b.getDefaultState().get(FillingUnitBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/filling_unit_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/filling_unit.png");
    }

    @Override
    public Identifier getAnimationResource(FillingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/filling_unit.animation.json");
    }

    @Override
    public RenderLayer getRenderType(FillingUnitBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
