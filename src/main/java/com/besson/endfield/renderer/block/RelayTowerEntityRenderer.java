package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.RelayTowerBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class RelayTowerEntityRenderer implements BlockEntityRenderer<RelayTowerBlockEntity> {
    private final RelayTowerRenderer geckoRenderer;

    public RelayTowerEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.geckoRenderer = new RelayTowerRenderer(context);
    }

    @Override
    public void render(RelayTowerBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        geckoRenderer.render(entity, tickDelta, matrices, vertexConsumers, light, overlay);

        if (entity.getConnectedNode() == null) return;

        BlockPos pos = entity.getPos().add(0, 10, 0);

        BlockPos connectedPos = null;
        BlockEntity connectedEntity;
        if (entity.getWorld() != null) {
            connectedEntity = entity.getWorld().getBlockEntity(entity.getConnectedNode());
            if (connectedEntity instanceof RelayTowerBlockEntity) {
                connectedPos = entity.getConnectedNode().add(0, 10, 0);
            } else {
                connectedPos = entity.getConnectedNode().add(0, 27, 0);
            }
        }

        if (connectedPos == null) return;

        Vec3d start = Vec3d.ofCenter(pos).subtract(Vec3d.of(entity.getPos()));
        Vec3d end = Vec3d.ofCenter(connectedPos).subtract(Vec3d.of(entity.getPos()));

        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getLines());

        Matrix4f matrix = matrices.peek().getPositionMatrix();

        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
        Vec3d cameraPos = camera.getPos();

        double dx = end.x - start.x;
        double dy = end.y - start.y;
        double dz = end.z - start.z;

        Vec3d dir = new Vec3d(dx, dy, dz).normalize();
        Vec3d toCam = cameraPos.subtract(start).normalize();
        Vec3d normal = dir.crossProduct(toCam).normalize();

        consumer.vertex(matrix, (float) start.x, (float) start.y, (float) start.z)
                .color(241, 237, 184, 255)
                .normal((float) normal.x, (float) normal.y, (float) normal.z)
                .next();
        consumer.vertex(matrix, (float) end.x, (float) end.y, (float) end.z)
                .color(241, 237, 184, 255)
                .normal((float) normal.x, (float) normal.y, (float) normal.z)
                .next();

    }
}
