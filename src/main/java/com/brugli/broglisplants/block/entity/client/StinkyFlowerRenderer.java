package com.brugli.broglisplants.block.entity.client;

import com.brugli.broglisplants.block.entity.custom.StinkyFlowerEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class StinkyFlowerRenderer extends GeoBlockRenderer<StinkyFlowerEntity> {
    public StinkyFlowerRenderer(BlockEntityRendererProvider.Context context) {
        super(new StinkyFlowerModel());
    }
}
