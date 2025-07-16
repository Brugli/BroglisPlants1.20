package com.brugli.broglisplants.entity.client;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.block.custom.SeedBomb;
import com.brugli.broglisplants.entity.custom.PrimedSeedBomb;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PrimedSeedBombRenderer extends EntityRenderer<PrimedSeedBomb> {
    private final BlockRenderDispatcher blockRenderer;

    public PrimedSeedBombRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.shadowRadius = 0.5F;
        this.blockRenderer = pContext.getBlockRenderDispatcher();
    }

    public void render(PrimedSeedBomb pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0F, 0.5F, 0.0F);
        int i = pEntity.getFuse();
        if ((float)i - pPartialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - pPartialTicks + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            pPoseStack.scale(f1, f1, f1);
        }

        pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        pPoseStack.translate(-0.5F, -0.5F, 0.5F);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        renderWhiteSolidBlock(this.blockRenderer, BroglisPlantsBlocks.SEED_BOMB.get().defaultBlockState().setValue(SeedBomb.AGE, 3), pPoseStack, pBuffer, pPackedLight, i / 5 % 2 == 0);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    public static void renderWhiteSolidBlock(BlockRenderDispatcher pBlockRenderDispatcher, BlockState pState, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, boolean pWhiteOverlay) {
        int i;
        if (pWhiteOverlay) {
            i = OverlayTexture.pack(OverlayTexture.u(0.1F), 10);
        } else {
            i = OverlayTexture.NO_OVERLAY;
        }

        pBlockRenderDispatcher.renderSingleBlock(pState, pPoseStack, pBuffer, pPackedLight, i);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull PrimedSeedBomb seedBomb) {
        return new ResourceLocation(BroglisPlants.MODID, "textures/entity/primed_seed_bomb.png");
    }
}
