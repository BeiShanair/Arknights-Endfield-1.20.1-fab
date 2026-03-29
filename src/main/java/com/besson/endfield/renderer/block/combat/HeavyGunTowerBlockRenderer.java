package com.besson.endfield.renderer.block.combat;

import com.besson.endfield.blockentity.custom.combat.HeavyGunTowerBlockEntity;
import com.besson.endfield.model.block.combat.HeavyGunTowerModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class HeavyGunTowerBlockRenderer extends GeoBlockRenderer<HeavyGunTowerBlockEntity> {
    public HeavyGunTowerBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new HeavyGunTowerModel());
    }

    @Override
    public void actuallyRender(MatrixStack poseStack, HeavyGunTowerBlockEntity animatable, BakedGeoModel model, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        GeoBone turretBone = getGeoModel().getBone("gunSupport").orElse(null);
        GeoBone barrelBone = getGeoModel().getBone("gun").orElse(null);
        if (turretBone != null) {
            turretBone.setRotY((float) Math.toRadians(animatable.getTurretYaw()));
        }
        if (barrelBone != null) {
            barrelBone.setRotX((float) Math.toRadians(animatable.getTurretPitch()));
        }
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
