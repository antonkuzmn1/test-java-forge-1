package com.antonkuzmn1.airplant.client.renderer;

import com.antonkuzmn1.airplant.blockentity.TestBlockEntity;
import com.antonkuzmn1.airplant.client.model.TestBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class TestBlockRenderer extends GeoBlockRenderer<TestBlockEntity> {
    public TestBlockRenderer() {
        super(new TestBlockModel());
    }

    @Override
    public RenderType getRenderType(
            TestBlockEntity animatable,
            ResourceLocation texture,
            MultiBufferSource bufferSource,
            float partialTick
    ) {
        return RenderType.entityTranslucent(texture);
    }
}
