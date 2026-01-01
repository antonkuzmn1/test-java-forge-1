package com.antonkuzmn1.airplant.registry;

import com.antonkuzmn1.airplant.AirPlantMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB,
            AirPlantMod.MODID
    );

    public static final RegistryObject<CreativeModeTab> AIR_PLANT_TAB = CREATIVE_MODE_TABS.register(
            "air_plant_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.airplant"))
                    .icon(() -> ModItems.AIR_PLANT_SEEDS.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.AIR_PLANT_SEEDS.get());
                        output.accept(ModItems.AIR_PLANT_POT.get());
                    })
                    .build()
    );
}
