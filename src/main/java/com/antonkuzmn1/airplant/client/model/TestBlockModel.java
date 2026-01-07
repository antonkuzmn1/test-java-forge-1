package com.antonkuzmn1.airplant.client.model;

import com.antonkuzmn1.airplant.blockentity.TestBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TestBlockModel extends GeoModel<TestBlockEntity> {
    @Override
    public ResourceLocation getModelResource(TestBlockEntity animatable) {
        return ResourceLocation.tryParse("airplant:geo/test.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TestBlockEntity animatable) {
        return ResourceLocation.tryParse("airplant:textures/block/test.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TestBlockEntity animatable) {
        return ResourceLocation.tryParse("airplant:animations/test.animation.json");
    }
}
