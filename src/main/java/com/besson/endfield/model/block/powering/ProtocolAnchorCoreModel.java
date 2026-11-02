package com.besson.endfield.model.block.powering;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.powering.ProtocolAnchorCoreBlock;
import com.besson.endfield.blockentity.custom.powering.ProtocolAnchorCoreBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ProtocolAnchorCoreModel extends GeoModel<ProtocolAnchorCoreBlockEntity> {
    @Override
    public Identifier getModelResource(ProtocolAnchorCoreBlockEntity protocolAnchorCoreBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/protocol_anchor_core.geo.json");
    }

    @Override
    public Identifier getTextureResource(ProtocolAnchorCoreBlockEntity protocolAnchorCoreBlockEntity) {
        Block block = protocolAnchorCoreBlockEntity.getCachedState().getBlock();
        if (block instanceof ProtocolAnchorCoreBlock b) {
            if (b.getDefaultState().get(ProtocolAnchorCoreBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/protocol_anchor_core_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/protocol_anchor_core.png");
    }

    @Override
    public Identifier getAnimationResource(ProtocolAnchorCoreBlockEntity protocolAnchorCoreBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/protocol_anchor_core.animation.json");
    }

    @Override
    public RenderLayer getRenderType(ProtocolAnchorCoreBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
