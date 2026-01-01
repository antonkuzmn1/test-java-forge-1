package com.antonkuzmn1.airplant.blocks;

import com.antonkuzmn1.airplant.blockentity.AirPlantPotBlockEntity;
import com.antonkuzmn1.airplant.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class AirPlantPotBlock extends Block implements EntityBlock {
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);
    public static final IntegerProperty HARVESTS = IntegerProperty.create("harvests", 0, 3);
    public static final BooleanProperty RECHARGING = BooleanProperty.create("recharging");

    private static final Logger LOGGER = LogUtils.getLogger();

    public AirPlantPotBlock() {
        super(BlockBehaviour.Properties.of());
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
        this.registerDefaultState(this.stateDefinition.any().setValue(HARVESTS, 0));
        this.registerDefaultState(this.stateDefinition.any().setValue(RECHARGING, false));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new AirPlantPotBlockEntity(pos, state);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public @NotNull VoxelShape getShape(
            @NotNull BlockState state,
            @NotNull BlockGetter world,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context
    ) {
        if (state.getValue(STAGE) == 0) {
            return Shapes.box(0.28, 0, 0.28, 0.72, 0.375, 0.72);
        }
        return Shapes.box(0.28, 0, 0.28, 0.72, 1, 0.72);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(
            @NotNull BlockState state,
            @NotNull BlockGetter world,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context
    ) {
        return Shapes.box(0.28, 0, 0.28, 0.72, 0.375, 0.72);
    }

    @Override
    public void spawnDestroyParticles(
            Level level,
            @NotNull Player player,
            @NotNull BlockPos pos,
            @NotNull BlockState state
    ) {
        if (level.isClientSide) {
            ClientLevel clientLevel = (ClientLevel) level;
            BlockState vanillaPot = Blocks.FLOWER_POT.defaultBlockState();
            // 2001 = vanilla block break particle event
            clientLevel.levelEvent(2001, pos, Block.getId(vanillaPot));
        }
    }

    @Override
    public InteractionResult use(
            @NotNull BlockState state,
            Level level,
            @NotNull BlockPos pos,
            @NotNull Player player,
            @NotNull InteractionHand hand,
            @NotNull BlockHitResult hit
    ) {
        ItemStack stack = player.getItemInHand(hand);
        int stage = state.getValue(STAGE);
        int harvests = state.getValue(HARVESTS);
        boolean recharging = state.getValue(RECHARGING);
        boolean isPlanted = stage > 0;
        boolean isMature = stage == 3;
        boolean isAirPlantSeeds = stack.is(ModItems.AIR_PLANT_SEEDS.get());
        boolean isWaterBottle = stack.is(Items.POTION)
                && (stack.getTag() != null)
                && stack.getTag().getString("Potion").equals("minecraft:water");

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof AirPlantPotBlockEntity pot)) return InteractionResult.PASS;

        LOGGER.debug("isMature: {}", isMature);
        LOGGER.debug("!recharging: {}", !recharging);
        LOGGER.debug("isWaterBottle: {}", isWaterBottle);

        if (level.isClientSide) {
            return ((isAirPlantSeeds && !isPlanted) || (isMature && !recharging && isWaterBottle))
                    ? InteractionResult.SUCCESS
                    : InteractionResult.CONSUME;
        }

        // seed logic
        if (isAirPlantSeeds && !isPlanted) {
            BlockState newState = state.setValue(STAGE, 1);
            level.setBlock(pos, newState, 3);

//            pot.syncFromBlockState();
//            pot.setChanged();

            level.sendBlockUpdated(pos, state, newState, 3);

//            LOGGER.info("Seeds used on Air Plant Pot at {}", pos);
//            player.sendSystemMessage(Component.literal("Seeds used on Air Plant Pot at " + pos));
        }

        // harvest logic
        if (isMature && !recharging && isWaterBottle) { // Cannot resolve symbol 'pot'
            stack.shrink(1);
            ItemStack emptyBottle = new ItemStack(Items.GLASS_BOTTLE);
            if (!player.getInventory().add(emptyBottle)) {
                player.drop(emptyBottle, false);
            }

            harvests--;

            if (harvests <= 0) {
                recharging = true;
                pot.rechargeTicks = 20 * 20;
            }

            BlockState newState = state
                    .setValue(HARVESTS, harvests)
                    .setValue(RECHARGING, recharging);
            level.setBlock(pos, newState, 3);

//            pot.syncToBlockState();
//            pot.setChanged();

            level.sendBlockUpdated(pos, state, state, 3);
        }

        return InteractionResult.PASS;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level,
            @NotNull BlockState state,
            @NotNull BlockEntityType<T> type
    ) {
        return level.isClientSide ? null : (lvl, pos, st, be) -> {
            if (be instanceof AirPlantPotBlockEntity pot) {
                pot.tick(lvl, pos, st);
            }
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE, HARVESTS, RECHARGING);
    }
}
