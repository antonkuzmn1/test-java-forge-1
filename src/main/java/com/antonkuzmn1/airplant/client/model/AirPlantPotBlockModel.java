package com.antonkuzmn1.airplant.client.model;

import com.antonkuzmn1.airplant.blockentity.AirPlantPotBlockEntity;
import com.antonkuzmn1.airplant.blocks.AirPlantPotBlock;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import software.bernie.geckolib.model.GeoModel;

public class AirPlantPotBlockModel extends GeoModel<AirPlantPotBlockEntity> {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public ResourceLocation getModelResource(AirPlantPotBlockEntity animatable) {
        int stage = getStage(animatable);
        boolean recharging = isRecharging(animatable);
        if  (stage == 3 && recharging) {
            return ResourceLocation.tryParse("airplant:geo/small_pot_plant_recharging.geo.json");
        }
        return switch (stage) {
            case 0 -> ResourceLocation.tryParse("airplant:geo/small_pot_plant_0.geo.json");
            case 1 -> ResourceLocation.tryParse("airplant:geo/small_pot_plant_1.geo.json");
            case 2 -> ResourceLocation.tryParse("airplant:geo/small_pot_plant_2.geo.json");
            case 3 -> ResourceLocation.tryParse("airplant:geo/small_pot_plant_3.geo.json");
            default -> throw new IllegalStateException("Unexpected value: " + stage);
        };
    }

    @Override
    public ResourceLocation getTextureResource(AirPlantPotBlockEntity animatable) {
        int stage = getStage(animatable);
        boolean recharging = isRecharging(animatable);
        if  (stage == 3 && recharging) {
            return ResourceLocation.tryParse("airplant:textures/block/small_pot_plant_recharging.png");
        }
        return switch (stage) {
            case 0 -> ResourceLocation.tryParse("airplant:textures/block/small_pot_plant_0.png");
            case 1 -> ResourceLocation.tryParse("airplant:textures/block/small_pot_plant_1.png");
            case 2 -> ResourceLocation.tryParse("airplant:textures/block/small_pot_plant_2.png");
            case 3 -> ResourceLocation.tryParse("airplant:textures/block/small_pot_plant_3.png");
            default -> throw new IllegalStateException("Unexpected value: " + stage);
        };
    }

    @Override
    public ResourceLocation getAnimationResource(AirPlantPotBlockEntity animatable) {
        int stage = getStage(animatable);
        boolean recharging = isRecharging(animatable);
        if  (stage == 3 && recharging) {
            return ResourceLocation.tryParse("airplant:animations/small_pot_plant_recharging.animation.json");
        }
        return switch (stage) {
            case 0 -> ResourceLocation.tryParse("airplant:animations/small_pot_plant_0.animation.json");
            case 1 -> ResourceLocation.tryParse("airplant:animations/small_pot_plant_1.animation.json");
            case 2 -> ResourceLocation.tryParse("airplant:animations/small_pot_plant_2.animation.json");
            case 3 -> ResourceLocation.tryParse("airplant:animations/small_pot_plant_3.animation.json");
            default -> throw new IllegalStateException("Unexpected value: " + stage);
        };
    }

    private int getStage(AirPlantPotBlockEntity animatable) {
        int stage = 0;
        assert animatable.getLevel() != null;
        var state = animatable.getLevel().getBlockState(animatable.getBlockPos());
        if (state.getBlock() instanceof AirPlantPotBlock) {
            stage = state.getValue(AirPlantPotBlock.STAGE);
        }
        return stage;
    }

    private boolean isRecharging(AirPlantPotBlockEntity animatable) {
        boolean recharging = false;
        assert animatable.getLevel() != null;
        var state = animatable.getLevel().getBlockState(animatable.getBlockPos());
        if (state.getBlock() instanceof AirPlantPotBlock) {
            recharging = state.getValue(AirPlantPotBlock.RECHARGING);
        }
        return recharging;
    }
}
