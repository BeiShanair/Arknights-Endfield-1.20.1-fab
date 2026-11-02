package com.besson.endfield.model.block.resourcing;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.resourcing.ElectricMiningRigBlock;
import com.besson.endfield.blockentity.custom.resourcing.ElectricMiningRigBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ElectricMiningRigModel extends GeoModel<ElectricMiningRigBlockEntity> {
    @Override
    public Identifier getModelResource(ElectricMiningRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/electric_mining_rig.geo.json");
    }

    @Override
    public Identifier getTextureResource(ElectricMiningRigBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof ElectricMiningRigBlock b) {
            if (b.getDefaultState().get(ElectricMiningRigBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_mining_rig_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_mining_rig.png");
    }

    @Override
    public Identifier getAnimationResource(ElectricMiningRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/electric_mining_rig.animation.json");
    }

    @Override
    public RenderLayer getRenderType(ElectricMiningRigBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
