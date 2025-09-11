package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.PortableOriginiumRigBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.RotationAxis;

public class PortableOriginiumRigEntityRenderer implements BlockEntityRenderer<PortableOriginiumRigBlockEntity> {
    private final PortableOriginiumRigRenderer geckoRenderer;

    public PortableOriginiumRigEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.geckoRenderer = new PortableOriginiumRigRenderer(context);
    }

    @Override
    public void render(PortableOriginiumRigBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        geckoRenderer.render(entity, tickDelta, matrices, vertexConsumers, light, overlay);

        if (!entity.isWorking()) {
            matrices.push();
            matrices.translate(0.5, 2.5, 0.5);

            MinecraftClient mc = MinecraftClient.getInstance();
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-mc.player.getYaw()));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(mc.player.getPitch()));
            matrices.scale(0.5f, 0.5f, 0.5f);

            mc.getItemRenderer().renderItem(
                    new ItemStack(Items.BARRIER),
                    ModelTransformationMode.GUI,
                    light, overlay,
                    matrices, vertexConsumers,
                    mc.world, 0
            );

            matrices.pop();
        }
    }
}
