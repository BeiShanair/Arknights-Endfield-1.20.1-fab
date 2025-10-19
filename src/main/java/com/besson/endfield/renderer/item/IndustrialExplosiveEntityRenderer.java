package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.IndustrialExplosiveEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class IndustrialExplosiveEntityRenderer extends EntityRenderer<IndustrialExplosiveEntity> {
    private final ItemRenderer renderer;

    public IndustrialExplosiveEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.renderer = ctx.getItemRenderer();
    }

    @Override
    public void render(IndustrialExplosiveEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - entity.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-entity.getPitch()));

        matrices.scale(1f, 1f, 1f);

        this.renderer.renderItem(
                entity.getStack(),
                ModelTransformationMode.GROUND,
                light,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                0);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(IndustrialExplosiveEntity entity) {
        return new Identifier("textures/misc/empty.png");
    }
}
