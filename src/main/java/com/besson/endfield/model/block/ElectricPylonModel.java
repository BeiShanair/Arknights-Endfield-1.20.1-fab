package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.ElectricPylonBlock;
import com.besson.endfield.blockentity.custom.ElectricPylonBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ElectricPylonModel extends GeoModel<ElectricPylonBlockEntity> {
    @Override
    public Identifier getModelResource(ElectricPylonBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/electric_pylon.geo.json");
    }

    @Override
    public Identifier getTextureResource(ElectricPylonBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof ElectricPylonBlock b) {
            if (b.getDefaultState().get(ElectricPylonBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_pylon_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_pylon.png");
    }

    @Override
    public Identifier getAnimationResource(ElectricPylonBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/electric_pylon.animation.json");
    }

    @Override
    public RenderLayer getRenderType(ElectricPylonBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
