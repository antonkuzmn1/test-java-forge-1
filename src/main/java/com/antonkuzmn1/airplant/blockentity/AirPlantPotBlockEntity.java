package com.antonkuzmn1.airplant.blockentity;

import com.antonkuzmn1.airplant.blocks.AirPlantPotBlock;
import com.antonkuzmn1.airplant.registry.ModBlockEntities;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class AirPlantPotBlockEntity extends BlockEntity implements GeoBlockEntity {
    private static final Logger LOGGER = LogUtils.getLogger();

    public int plantState = 0;

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
}
