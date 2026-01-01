package com.antonkuzmn1.airplant.blocks;

import com.antonkuzmn1.airplant.blockentity.AirPlantPotBlockEntity;
import com.antonkuzmn1.airplant.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class AirPlantPotBlock extends Block implements EntityBlock {
    private static final Logger LOGGER = LogUtils.getLogger();

    public AirPlantPotBlock() {
        super(BlockBehaviour.Properties.of());
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
        boolean isAirPlantSeeds = stack.is(ModItems.AIR_PLANT_SEEDS.get());

        if (level.isClientSide) {
            return isAirPlantSeeds ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
        }

        if (isAirPlantSeeds) {
            LOGGER.info("Seeds used on Air Plant Pot at {}", pos);
            player.sendSystemMessage(Component.literal("Seeds used on Air Plant Pot at " + pos));
        }

        return InteractionResult.PASS;
    }
}
