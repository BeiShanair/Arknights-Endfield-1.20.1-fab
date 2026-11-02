package com.besson.endfield.model.block.resourcing;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.resourcing.PortableOriginiumRigBlock;
import com.besson.endfield.blockentity.custom.resourcing.PortableOriginiumRigBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PortableOriginiumModel extends GeoModel<PortableOriginiumRigBlockEntity> {
    @Override
    public Identifier getModelResource(PortableOriginiumRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/portable_originium_rig.geo.json");
    }

    @Override
    public Identifier getTextureResource(PortableOriginiumRigBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof PortableOriginiumRigBlock b) {
            if (b.getDefaultState().get(PortableOriginiumRigBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/portable_originium_rig_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/portable_originium_rig.png");
    }

    @Override
    public Identifier getAnimationResource(PortableOriginiumRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/portable_originium_rig.animation.json");
    }

    @Override
    public RenderLayer getRenderType(PortableOriginiumRigBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
