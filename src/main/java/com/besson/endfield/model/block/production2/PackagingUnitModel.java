package com.besson.endfield.model.block.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.production2.PackagingUnitBlock;
import com.besson.endfield.blockentity.custom.production2.PackagingUnitBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PackagingUnitModel extends GeoModel<PackagingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(PackagingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/packaging_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(PackagingUnitBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof PackagingUnitBlock b) {
            if (b.getDefaultState().get(PackagingUnitBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/packaging_unit_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/packaging_unit.png");
    }

    @Override
    public Identifier getAnimationResource(PackagingUnitBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(PackagingUnitBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
