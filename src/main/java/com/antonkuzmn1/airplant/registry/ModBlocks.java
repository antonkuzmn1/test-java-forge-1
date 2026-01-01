package com.antonkuzmn1.airplant.registry;

import com.antonkuzmn1.airplant.AirPlantMod;
import com.antonkuzmn1.airplant.blocks.AirPlantPotBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS,
            AirPlantMod.MODID
    );

    public static final RegistryObject<Block> AIR_PLANT_POT = BLOCKS.register(
            "air_plant_pot",
            AirPlantPotBlock::new
    );
}
