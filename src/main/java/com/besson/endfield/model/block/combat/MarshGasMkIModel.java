package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.combat.MarshGasMkIBlock;
import com.besson.endfield.blockentity.custom.combat.MarshGasMkIBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MarshGasMkIModel extends GeoModel<MarshGasMkIBlockEntity> {
    @Override
    public Identifier getModelResource(MarshGasMkIBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/marsh_gas_mk_i.geo.json");
    }

    @Override
    public Identifier getTextureResource(MarshGasMkIBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof MarshGasMkIBlock b) {
            if (b.getDefaultState().get(MarshGasMkIBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/marsh_gas_mk_i_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/marsh_gas_mk_i.png");
    }

    @Override
    public Identifier getAnimationResource(MarshGasMkIBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(MarshGasMkIBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
