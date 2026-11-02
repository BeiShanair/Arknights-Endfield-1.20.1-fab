package com.besson.endfield.renderer.block.powering;

import com.besson.endfield.blockentity.custom.powering.*;
import com.besson.endfield.config.ModClientConfig;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class XiranitePylonEntityRenderer implements BlockEntityRenderer<XiranitePylonBlockEntity> {
    
    private final XiranitePylonRenderer geckoRenderer;
    
    public XiranitePylonEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.geckoRenderer = new XiranitePylonRenderer(context);
    }
    
    @Override
    public void render(XiranitePylonBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        geckoRenderer.render(entity, tickDelta, matrices, vertexConsumers, light, overlay);

        if (entity.getConnectedNode() == null) return;

        BlockPos pos = entity.getPos().add(0, 7, 0);

        BlockPos connectedPos = null;
        BlockEntity be;
        if (entity.getWorld() != null) {
            be = entity.getWorld().getBlockEntity(entity.getConnectedNode());
            if (be instanceof BaseRelayBlockEntity) {
                connectedPos = entity.getConnectedNode().add(0, 10, 0);
            } else if (be instanceof ProtocolAnchorCoreBlockEntity){
                connectedPos = entity.getConnectedNode().add(0, 27, 0);
            } else if (be instanceof BasePylonBlockEntity) {
                connectedPos = entity.getConnectedNode().add(0, 7, 0);
            }
        }

        if (connectedPos == null) return;

        Vec3d start = Vec3d.ofCenter(pos).subtract(Vec3d.of(entity.getPos()));
        Vec3d end = Vec3d.ofCenter(connectedPos).subtract(Vec3d.of(entity.getPos()));

        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getLines());

        Matrix4f matrix4f = matrices.peek().getPositionMatrix();

        final int yr = 43, yg = 152, yb = 8;
        final int br = 215, bg = 255, bb = 183;

        Vec3d delta = end.subtract(start);
        double length = delta.length();
        if (length < 1.0E-8) return;

        Vec3d normal = delta.normalize();
        float nx = (float) normal.x;
        float ny = (float) normal.y;
        float nz = (float) normal.z;

        Vec3d mid = start.add(delta.multiply(0.5));

        drawWireSegment(consumer, matrix4f, start, mid, yr, yg, yb, br, bg, bb, nx, ny, nz);
        drawWireSegment(consumer, matrix4f, mid, end, br, bg, bb, yr, yg, yb, nx, ny, nz);
    }

    // 画一小段线：a 处颜色为 (ar,ag,ab)，b 处为 (br,bg,bb)，两端间由渲染器线性插值。
    private static void drawWireSegment(
            VertexConsumer consumer,
            Matrix4f matrix,
            Vec3d a,
            Vec3d b,
            int ar, int ag, int ab,
            int br, int bg, int bb,
            float nx, float ny, float nz) {
        consumer.vertex(matrix, (float) a.x, (float) a.y, (float) a.z)
                .color(ar, ag, ab, 255)
                .normal(nx, ny, nz)
                .next();
        consumer.vertex(matrix, (float) b.x, (float) b.y, (float) b.z)
                .color(br, bg, bb, 255)
                .normal(nx, ny, nz)
                .next();
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
