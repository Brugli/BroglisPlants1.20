package com.brugli.broglisplants.block.entity.custom;

import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.block.custom.SeedBomb;
import com.brugli.broglisplants.block.entity.BroglisPlantsBlockEntities;
import com.brugli.broglisplants.entity.custom.PrimedSeedBomb;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class SeedBombEntity extends BlockEntity {

    public SeedBombEntity(BlockPos pPos, BlockState pBlockState) {
        super(BroglisPlantsBlockEntities.SEED_BOMB_ENTITY.get(), pPos, pBlockState);
    }

    public void tick(Level serverLevel, BlockPos pPos) {
        int i = 1;
        do {
            ++i;
        } while (serverLevel.getBlockState(pPos.relative(Direction.DOWN, i)).canBeReplaced()
                || serverLevel.getBlockState(pPos.relative(Direction.DOWN, i)).is(Blocks.AIR));

        AABB aabb = (new AABB(pPos)).expandTowards(0.0D, -i, 0.0D);
        List<Player> playerList = serverLevel.getEntitiesOfClass(Player.class, aabb);
        List<Monster> monsterList = serverLevel.getEntitiesOfClass(Monster.class, aabb);
        if ((!playerList.isEmpty() || !monsterList.isEmpty()) && serverLevel.getBlockState(pPos).getValue(SeedBomb.AGE) == 3) {
            serverLevel.setBlock(pPos, BroglisPlantsBlocks.SEED_BOMB.get().defaultBlockState().setValue(SeedBomb.AGE, 0), 2);
            if (!serverLevel.isClientSide) {
                PrimedSeedBomb seedBomb = new PrimedSeedBomb(serverLevel, (double)pPos.getX() + 0.5D, (double)pPos.getY(), (double)pPos.getZ() + 0.5D, (Player)null);
                serverLevel.addFreshEntity(seedBomb);
                serverLevel.playSound((Player)null, seedBomb.getX(), seedBomb.getY(), seedBomb.getZ(), SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 0.5F, 1.0F);
                serverLevel.gameEvent((Player)null, GameEvent.PRIME_FUSE, pPos);
            }
        }
    }
}