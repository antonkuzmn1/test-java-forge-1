package com.antonkuzmn1.airplant.client.renderer;

import com.antonkuzmn1.airplant.client.model.AirPlantPotItemModel;
import com.antonkuzmn1.airplant.items.AirPlantPotItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AirPlantPotItemRenderer extends GeoItemRenderer<AirPlantPotItem> {
    public AirPlantPotItemRenderer() {
        super(new AirPlantPotItemModel());
    }

    @Override
    public void renderByItem(
            ItemStack stack,
            ItemDisplayContext transformType,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            int packedOverlay
    ) {
        poseStack.pushPose();

        poseStack.translate(0.25f, 0.25f, 0.25f);
        poseStack.scale(0.5f, 0.5f, 0.5f);

        if (transformType == ItemDisplayContext.GUI) {
            poseStack.translate(-0.6f, -0.6f, 0f);
            poseStack.scale(1.5f, 1.5f, 1.5f);
            poseStack.mulPose(new Quaternionf().rotateXYZ(
                    (float) Math.toRadians(30f),
                    (float) Math.toRadians(45f),
                    0f
            ));
        }

        if (transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND
                || transformType == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND
        ) {
            poseStack.translate(-0.25f, 0.25f, -0.25f);
            poseStack.mulPose(new Quaternionf().rotateXYZ(
                    (float) Math.toRadians(60f),
                    (float) Math.toRadians(45f),
                    0f
            ));
        }

        super.renderByItem(stack, transformType, poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();
    }
}
