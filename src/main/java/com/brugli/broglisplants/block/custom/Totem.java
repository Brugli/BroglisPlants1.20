package com.brugli.broglisplants.block.custom;

import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.common.ToolAction;

import javax.annotation.Nullable;

public class Totem extends FlammableRotatedPillarBlock {

    public static final IntegerProperty STYLE = IntegerProperty.create("style", 1, 3);

    public Totem(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STYLE, RandomSource.create().nextInt(1, 4)).setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(BroglisPlantsBlocks.TOTEM.get())) {
                return BroglisPlantsBlocks.TOTEM.get().defaultBlockState().setValue(STYLE, RandomSource.create().nextInt(1,4)).setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(STYLE, AXIS);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(STYLE, RandomSource.create().nextInt(1,4)).setValue(AXIS, pContext.getClickedFace().getAxis());
    }
}

//{
//        "variants": {
//        "axis=x": [
//        { "model": "broglisplants:block/totem_horizontal", "x": 90, "y": 90},
//        { "model": "broglisplants:block/totem_horizontal_2", "x": 90, "y": 90},
//        { "model": "broglisplants:block/totem_horizontal_3", "x": 90, "y": 90}
//        ],
//        "axis=y": [
//        { "model": "broglisplants:block/totem"},
//        { "model": "broglisplants:block/totem_2"},
//        { "model": "broglisplants:block/totem_3"}
//        ],
//        "axis=z": [
//        { "model": "broglisplants:block/totem_horizontal", "x": 90},
//        { "model": "broglisplants:block/totem_horizontal_2", "x": 90},
//        { "model": "broglisplants:block/totem_horizontal_3", "x": 90}
//        ]
//        }
//        }
