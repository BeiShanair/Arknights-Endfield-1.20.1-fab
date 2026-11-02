package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.combat.SurgeTowerBlock;
import com.besson.endfield.blockentity.custom.combat.SurgeTowerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SurgeTowerModel extends GeoModel<SurgeTowerBlockEntity> {
    @Override
    public Identifier getModelResource(SurgeTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/surge_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(SurgeTowerBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof SurgeTowerBlock b) {
            if (b.getDefaultState().get(SurgeTowerBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/surge_tower_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/surge_tower.png");
    }

    @Override
    public Identifier getAnimationResource(SurgeTowerBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(SurgeTowerBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
