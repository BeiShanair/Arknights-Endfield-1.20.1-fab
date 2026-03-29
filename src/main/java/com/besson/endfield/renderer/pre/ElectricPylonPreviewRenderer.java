package com.besson.endfield.renderer.pre;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.powering.ElectricPylonBlock;
import com.besson.endfield.blockentity.custom.powering.ElectricPylonBlockEntity;
import com.besson.endfield.renderer.block.powering.ElectricPylonEntityRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class ElectricPylonPreviewRenderer {
    private static ElectricPylonBlockEntity fakeBe = null;

    public static void register() {
        WorldRenderEvents.AFTER_ENTITIES.register(ElectricPylonPreviewRenderer::renderPreview);
    }

    private static void renderPreview(WorldRenderContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        ItemStack stack = client.player.getMainHandStack();
        if (stack.getItem() != ModBlocks.ELECTRIC_PYLON.asItem()) return;

        HitResult hit = client.crosshairTarget;
        if (!(hit instanceof BlockHitResult bhr)) return;

        BlockPos placePos = bhr.getBlockPos().offset(bhr.getSide());

        if (!client.world.getBlockState(placePos).canPlaceAt(client.world, placePos)) return;

        Direction facing = client.player.getHorizontalFacing().getOpposite();
        BlockState previewState = ModBlocks.ELECTRIC_PYLON.getDefaultState().with(ElectricPylonBlock.FACING, facing).with(ElectricPylonBlock.PREVIEW, true);

        if (fakeBe == null) {
            fakeBe = new ElectricPylonBlockEntity(placePos, previewState);
        }
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

        ElectricPylonEntityRenderer renderer =
                (ElectricPylonEntityRenderer) MinecraftClient.getInstance().getBlockEntityRenderDispatcher().get(fakeBe);

        if (renderer != null) {
            renderer.render(fakeBe, 0, matrixStack, vcp, 15728880, OverlayTexture.DEFAULT_UV);
        }

        // 你可以调整 padding（让框比方块略大一点）
        double pad1 = 0.002;
        Box box = new Box(
                -pad1 - 0.5, -pad1, -pad1 - 0.5,
                1.5 + pad1, 8 + pad1, 1.5 + pad1
        );

        // 使用线框层
        VertexConsumer outline = vcp.getBuffer(RenderLayer.getLines());

        // 颜色 RGBA（浅绿色）
        float r1 = 0.3f;
        float g1 = 1.0f;
        float b1 = 0.3f;
        float a1 = 0.4f;

        WorldRenderer.drawBox(matrixStack, outline, box, r1, g1, b1, a1);

        matrixStack.pop();

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.disableBlend();
    }
}
