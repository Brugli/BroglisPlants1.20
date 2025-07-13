package com.brugli.broglisplants.datagen;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BroglisPlantsBlockTagsGenerators extends BlockTagsProvider {

    public BroglisPlantsBlockTagsGenerators(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BroglisPlants.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(BlockTags.REPLACEABLE_BY_TREES)
                .add(BroglisPlantsBlocks.THORN.get());

        this.tag(BlockTags.LEAVES)
                .add(BroglisPlantsBlocks.SANDBOX_LEAVES.get());

        this.tag(BlockTags.LOGS)
                .add(BroglisPlantsBlocks.SANDBOX_LOG.get())
                .add(BroglisPlantsBlocks.SANDBOX_WOOD.get())
                .add(BroglisPlantsBlocks.TOTEM.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(BroglisPlantsBlocks.SANDBOX_LOG.get())
                .add(BroglisPlantsBlocks.SANDBOX_WOOD.get())
                .add(BroglisPlantsBlocks.TOTEM.get());
    }

    @Override
    public String getName() {
        return "Block Tags";
    }
}
