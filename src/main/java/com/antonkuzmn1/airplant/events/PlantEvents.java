package com.antonkuzmn1.airplant.events;

import com.antonkuzmn1.airplant.AirPlantMod;
import com.antonkuzmn1.airplant.registry.ModBlocks;
import com.antonkuzmn1.airplant.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = AirPlantMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlantEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
//        LOGGER.info("onRightClickBlock");
//        Level world = event.getLevel();
//        BlockPos pos = event.getPos();
//        Player player = event.getEntity();
//        ItemStack stack = event.getItemStack();
//
//        if (world.isClientSide) return;
//        if (!(world.getBlockState(pos).getBlock() instanceof FlowerPotBlock)) return;
//        if (!stack.is(ModItems.AIR_PLANT_SEEDS.get())) return;
//
//        world.setBlock(pos, ModBlocks.POTTED_AIR_PLANT.get().defaultBlockState(), 3);
//        stack.shrink(1);
//
//        event.setCanceled(true);
//        event.setCancellationResult(InteractionResult.SUCCESS);
    }
}
