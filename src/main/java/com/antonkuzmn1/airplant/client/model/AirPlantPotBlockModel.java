package com.antonkuzmn1.airplant.client.model;

import com.antonkuzmn1.airplant.blockentity.AirPlantPotBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AirPlantPotBlockModel extends GeoModel<AirPlantPotBlockEntity> {
    @Override
    public ResourceLocation getModelResource(AirPlantPotBlockEntity animatable) {
        return ResourceLocation.tryParse("airplant:geo/small_pot_plant_3.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AirPlantPotBlockEntity animatable) {
        return ResourceLocation.tryParse("airplant:textures/block/small_pot_plant_3.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AirPlantPotBlockEntity animatable) {
        return ResourceLocation.tryParse("airplant:animations/small_pot_plant_3.animation.json");
    }
}
