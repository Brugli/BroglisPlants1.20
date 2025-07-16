package com.brugli.broglisplants.block.entity.client;

import com.brugli.broglisplants.block.entity.custom.FlytrapEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FlytrapRenderer extends GeoBlockRenderer<FlytrapEntity> {
    public FlytrapRenderer(BlockEntityRendererProvider.Context context) {
        super(new FlytrapModel());
    }

    @Override
    public void preRender(PoseStack poseStack, FlytrapEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        assert animatable.getLevel() != null;
        Vec3 vec3 = animatable.getBlockState().getOffset(animatable.getLevel(), animatable.getBlockPos());
        poseStack.translate(vec3.x, vec3.y, vec3.z);
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
