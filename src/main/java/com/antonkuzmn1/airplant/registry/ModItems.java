package com.antonkuzmn1.airplant.registry;

import com.antonkuzmn1.airplant.AirPlantMod;
import com.antonkuzmn1.airplant.items.AirPlantPotItem;
import com.antonkuzmn1.airplant.items.AirPlantSeedsItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            AirPlantMod.MODID
    );

    public static final RegistryObject<AirPlantSeedsItem> AIR_PLANT_SEEDS = ITEMS.register(
            "air_plant_seeds",
            AirPlantSeedsItem::new
    );

    public static final RegistryObject<AirPlantPotItem> AIR_PLANT_POT = ITEMS.register(
            "air_plant_pot",
            AirPlantPotItem::new
    );
}
