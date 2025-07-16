package com.brugli.broglisplants.datagen;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BroglisPlantsItemTagGenerators extends ItemTagsProvider {
    public BroglisPlantsItemTagGenerators(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                                          CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, BroglisPlants.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(BroglisPlantsBlocks.SANDBOX_LOG.get().asItem())
                .add(BroglisPlantsBlocks.SANDBOX_WOOD.get().asItem())
                .add(BroglisPlantsBlocks.TOTEM.get().asItem());
    }
}