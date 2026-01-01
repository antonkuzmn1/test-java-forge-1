package com.antonkuzmn1.airplant;

import com.antonkuzmn1.airplant.registry.ModBlockEntities;
import com.antonkuzmn1.airplant.registry.ModBlocks;
import com.antonkuzmn1.airplant.registry.ModCreativeTabs;
import com.antonkuzmn1.airplant.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(AirPlantMod.MODID)
public class AirPlantMod {
    public static final String MODID = "airplant";

    private static final Logger LOGGER = LogUtils.getLogger();

    public AirPlantMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        GeckoLib.initialize();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}