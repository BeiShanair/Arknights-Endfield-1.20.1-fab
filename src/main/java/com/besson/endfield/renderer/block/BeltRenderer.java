package com.besson.endfield.renderer.block;

import com.besson.endfield.block.custom.logicitis.BeltBlock;
import com.besson.endfield.block.custom.logicitis.BeltShape;
import com.besson.endfield.blockentity.custom.logicitis.BeltBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

public class BeltRenderer implements BlockEntityRenderer<BeltBlockEntity> {

    public BeltRenderer(BlockEntityRendererFactory.Context context){

    }

    @Override
    public void render(BeltBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemStack stack = entity.getStoredItem();

        if (stack.isEmpty()) return;

        float progress = entity.progress;

        matrices.push();

        applyTransform(entity, progress, matrices);
        renderItem(stack, matrices, vertexConsumers, light, overlay);

        matrices.pop();
    }
    
    private void applyTransform(BeltBlockEntity be, float progress, MatrixStack matrices) {
//        Direction dir = be.getCachedState().get(BeltBlock.FACING);
//
//        float offset = progress - 0.5f;
//
//        float x = 0.5f;
//        float y = 0.07f;
//        float z = 0.5f;
//
//        switch (dir) {
//            case NORTH -> z += offset;
//            case SOUTH -> z -= offset;
//            case WEST  -> x += offset;
//            case EAST  -> x -= offset;
//        }
//
//        matrices.translate(x, y, z);
//        matrices.scale(0.4f, 0.4f, 0.4f);

        BlockState state = be.getCachedState();
        BeltShape shape = state.get(BeltBlock.SHAPE);

        Direction from = be.getTravelDirection();
        if (from == null) return;

        Direction to = BeltBlock.getNextDirection(shape, from);
        if (to == null) return;

        // 起点偏移
        Vec3d start = getOffsetVec(from, 0.5f);

        // 终点偏移
        Vec3d end = getOffsetVec(to, 0.5f);

        // 上坡处理
        if (BeltBlock.isAscendingTowards(shape, to)) {
            end = new Vec3d(end.getX(), 1f, end.getZ());
        }
        if (BeltBlock.isAscendingTowards(shape, from)) {
            start = new Vec3d(start.getX(), 1f, start.getZ());
        }
        // 插值
        float x = lerp(progress, (float) start.getX(), (float) end.getX());
        float y = lerp(progress, (float) start.getY(), (float) end.getY());
        float z = lerp(progress, (float) start.getZ(), (float) end.getZ());
        
        matrices.translate(0.5f + x, 0.07f + y, 0.5f + z);
        matrices.scale(0.4f, 0.4f, 0.4f);
    }

    private float lerp(float t, float a, float b) {
        return a + t * (b - a);
    }

    private Vec3d getOffsetVec(Direction dir, float distance) {

        return switch (dir) {
            case NORTH -> new Vec3d(0, 0, -distance);
            case SOUTH -> new Vec3d(0, 0, distance);
            case WEST  -> new Vec3d(-distance, 0, 0);
            case EAST  -> new Vec3d(distance, 0, 0);
            default -> new Vec3d(0, 0, 0);
        };
    }
    
    private void renderItem(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertices, int light, int overlay) {

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));

        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.FIXED, light, overlay, matrices, vertices, null, 0);
    }
}
