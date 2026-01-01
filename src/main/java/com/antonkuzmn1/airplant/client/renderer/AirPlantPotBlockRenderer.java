package com.antonkuzmn1.airplant.client.renderer;

import com.antonkuzmn1.airplant.blockentity.AirPlantPotBlockEntity;
import com.antonkuzmn1.airplant.client.model.AirPlantPotBlockModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AirPlantPotBlockRenderer extends GeoBlockRenderer<AirPlantPotBlockEntity> {
    public AirPlantPotBlockRenderer() {
        super(new AirPlantPotBlockModel());
    }
}
