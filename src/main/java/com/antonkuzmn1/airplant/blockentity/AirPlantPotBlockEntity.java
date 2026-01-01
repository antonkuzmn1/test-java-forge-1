package com.antonkuzmn1.airplant.blockentity;

import com.antonkuzmn1.airplant.blocks.AirPlantPotBlock;
import com.antonkuzmn1.airplant.registry.ModBlockEntities;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class AirPlantPotBlockEntity extends BlockEntity implements GeoBlockEntity {
    public int growCooldown = 200;
    public int plantState = 0;

    private static final Logger LOGGER = LogUtils.getLogger();
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public AirPlantPotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AIR_PLANT_POT.get(), pos, state);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        syncFromBlockState();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public void syncFromBlockState() {
        if (level == null) return;

        BlockState state = level.getBlockState(worldPosition);

        if (!(state.getBlock() instanceof AirPlantPotBlock)) {
            return;
        }

        plantState = state.getValue(AirPlantPotBlock.STAGE);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) return;

        int stage = state.getValue(AirPlantPotBlock.STAGE);
        if (stage > 0 && stage < 3) {
            if (growCooldown <= 0) {
                level.setBlock(pos, state.setValue(AirPlantPotBlock.STAGE, stage + 1), 3);
                growCooldown = 20 * 10; // 10 seconds
            } else growCooldown--;
        }
    }
}
