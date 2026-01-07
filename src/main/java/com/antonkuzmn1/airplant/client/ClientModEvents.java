package com.antonkuzmn1.airplant.client;

import com.antonkuzmn1.airplant.AirPlantMod;
import com.antonkuzmn1.airplant.client.renderer.AirPlantPotBlockRenderer;
import com.antonkuzmn1.airplant.client.renderer.TestBlockRenderer;
import com.antonkuzmn1.airplant.registry.ModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AirPlantMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerBlockEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                ModBlockEntities.AIR_PLANT_POT.get(),
                ctx -> new AirPlantPotBlockRenderer()
        );
        event.registerBlockEntityRenderer(
                ModBlockEntities.TEST.get(),
                ctx -> new TestBlockRenderer()
        );
    }
}
