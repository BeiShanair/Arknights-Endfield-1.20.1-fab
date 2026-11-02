package com.besson.endfield.renderer.pre;

import com.besson.endfield.util.MultiBlockPlacementUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.block.entity.BlockEntity;

public abstract class BasePreviewRenderer<T extends BlockEntity> {
    private T fakeBe = null;

    
    protected void start() {
        WorldRenderEvents.AFTER_ENTITIES.register(this::renderPreview);
    }

    private void renderPreview(WorldRenderContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        ItemStack stack = client.player.getMainHandStack();
        if (!matchesItem(stack)) return;

        HitResult hit = client.crosshairTarget;
        if (!(hit instanceof BlockHitResult bhr)) return;

        BlockPos placePos = bhr.getBlockPos().offset(bhr.getSide());

        World world = client.world;
        if (!MultiBlockPlacementUtil.isReplaceable(world, placePos)) return;

        BlockState previewState = getPreviewState(stack, bhr, client);

        fakeBe = createFakeBe(placePos, previewState);
        if (fakeBe == null) return;
        fakeBe.setCachedState(previewState);

        var camera = context.camera();
        double camX = camera.getPos().x;
        double camY = camera.getPos().y;
        double camZ = camera.getPos().z;

        MatrixStack matrixStack = context.matrixStack();
        matrixStack.push();
        matrixStack.translate(placePos.getX() - camX, placePos.getY() - camY, placePos.getZ() - camZ);

        VertexConsumerProvider.Immediate vcp = MinecraftClient.getInstance()
                .getBufferBuilders().getEntityVertexConsumers();
        
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1f, 1f, 1f, 0.5f);

        BlockEntityRenderer<T> renderer = (BlockEntityRenderer<T>) MinecraftClient.getInstance().getBlockEntityRenderDispatcher().get(fakeBe);

        if (renderer != null) {
            renderer.render(fakeBe, 0, matrixStack, vcp, 15728880, OverlayTexture.DEFAULT_UV);
        }

        Box box = getOutlineBox(previewState);
        if (box != null) {
            VertexConsumer outline = vcp.getBuffer(RenderLayer.getLines());
            float[] col = getOutlineColor();
            WorldRenderer.drawBox(matrixStack, outline, box, col[0], col[1], col[2], col[3]);
        }

        matrixStack.pop();

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.disableBlend();
    }

    protected float[] getOutlineColor() {
        return new float[]{0.3f, 1.0f, 0.3f, 0.4f};
    }

    protected abstract boolean matchesItem(ItemStack stack);

    protected abstract BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client);

    protected abstract T createFakeBe(BlockPos pos, BlockState state);
    
    protected Box getOutlineBox(BlockState previewState) {
        double pad1 = 0.002;
        return new Box(-pad1, -pad1, -pad1, pad1, pad1, pad1);
    };
}
