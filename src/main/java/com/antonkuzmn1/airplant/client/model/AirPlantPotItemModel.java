package com.antonkuzmn1.airplant.client.model;

import com.antonkuzmn1.airplant.items.AirPlantPotItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AirPlantPotItemModel extends GeoModel<AirPlantPotItem> {
    @Override
    public ResourceLocation getModelResource(AirPlantPotItem animatable) {
        return ResourceLocation.tryParse("airplant:geo/small_pot_plant_0.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AirPlantPotItem animatable) {
        return ResourceLocation.tryParse("airplant:textures/block/small_pot_plant_0.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AirPlantPotItem animatable) {
        return ResourceLocation.tryParse("airplant:animations/small_pot_plant_0.animation.json");
    }
}
