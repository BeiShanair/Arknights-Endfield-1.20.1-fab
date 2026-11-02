package com.besson.endfield.mixin;

import com.besson.endfield.fluid.SewageFluid;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @Shadow
    private static float red;
    @Shadow
    private static float green;
    @Shadow
    private static float blue;

    @Inject(method = "render", at = @At(value = "TAIL"))
    private static void endfield$fluidFog(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness, CallbackInfo ci) {
        BlockPos pos = BlockPos.ofFloored(camera.getPos());
        FluidState fluidState = world.getFluidState(pos);
        if (fluidState.getFluid() instanceof SewageFluid) {
            red = 47f / 255f;
            green = 79f / 255f;
            blue = 79f / 255f;
        }
    }

    @Inject(method = "applyFog", at = @At("TAIL"))
    private static void endfield$applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        FluidState fluid = camera.getFocusedEntity().getWorld().getFluidState(camera.getBlockPos());
        if (fluid.getFluid() instanceof SewageFluid) {
            RenderSystem.setShaderFogStart(1.0F);
            RenderSystem.setShaderFogEnd(6.0F);
        }
    }
}
