package com.antonkuzmn1.airplant.registry;

import com.antonkuzmn1.airplant.AirPlantMod;
import com.antonkuzmn1.airplant.blockentity.AirPlantPotBlockEntity;
import com.antonkuzmn1.airplant.blockentity.TestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES,
            AirPlantMod.MODID
    );

    public static final RegistryObject<BlockEntityType<AirPlantPotBlockEntity>> AIR_PLANT_POT = BLOCK_ENTITIES.register(
            "air_plant_pot",
            () -> BlockEntityType.Builder.of(
                    AirPlantPotBlockEntity::new,
                    ModBlocks.AIR_PLANT_POT.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<TestBlockEntity>> TEST = BLOCK_ENTITIES.register(
            "test",
            () -> BlockEntityType.Builder.of(
                    TestBlockEntity::new,
                    ModBlocks.TEST.get()
            ).build(null)
    );
}
