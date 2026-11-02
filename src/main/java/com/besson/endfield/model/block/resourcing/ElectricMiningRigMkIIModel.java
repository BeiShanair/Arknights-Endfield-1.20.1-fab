package com.besson.endfield.model.block.resourcing;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.resourcing.ElectricMiningRigMkIIBlock;
import com.besson.endfield.blockentity.custom.resourcing.ElectricMiningRigMkIIBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ElectricMiningRigMkIIModel extends GeoModel<ElectricMiningRigMkIIBlockEntity> {
    @Override
    public Identifier getModelResource(ElectricMiningRigMkIIBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/electric_mining_rig_mk_ii.geo.json");
    }

    @Override
    public Identifier getTextureResource(ElectricMiningRigMkIIBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof ElectricMiningRigMkIIBlock b) {
            if (b.getDefaultState().get(ElectricMiningRigMkIIBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_mining_rig_mk_ii_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_mining_rig_mk_ii.png");
    }

    @Override
    public Identifier getAnimationResource(ElectricMiningRigMkIIBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/electric_mining_rig_mk_ii.animation.json");
    }

    @Override
    public RenderLayer getRenderType(ElectricMiningRigMkIIBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
