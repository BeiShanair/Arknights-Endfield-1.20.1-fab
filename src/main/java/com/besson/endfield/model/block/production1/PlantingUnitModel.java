package com.besson.endfield.model.block.production1;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.production1.PlantingUnitBlock;
import com.besson.endfield.blockentity.custom.production1.PlantingUnitBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PlantingUnitModel extends GeoModel<PlantingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(PlantingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/planting_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(PlantingUnitBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof PlantingUnitBlock b) {
            if (b.getDefaultState().get(PlantingUnitBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/planting_unit_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/planting_unit.png");
    }

    @Override
    public Identifier getAnimationResource(PlantingUnitBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(PlantingUnitBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
