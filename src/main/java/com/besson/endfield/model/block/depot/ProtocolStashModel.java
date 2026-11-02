package com.besson.endfield.model.block.depot;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.depot.ProtocolStashBlock;
import com.besson.endfield.blockentity.custom.depot.ProtocolStashBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ProtocolStashModel extends GeoModel<ProtocolStashBlockEntity> {
    @Override
    public Identifier getModelResource(ProtocolStashBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/protocol_stash.geo.json");
    }

    @Override
    public Identifier getTextureResource(ProtocolStashBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof ProtocolStashBlock b) {
            if (b.getDefaultState().get(ProtocolStashBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/protocol_stash_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/protocol_stash.png");
    }

    @Override
    public Identifier getAnimationResource(ProtocolStashBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(ProtocolStashBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
