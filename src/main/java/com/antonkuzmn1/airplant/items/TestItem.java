package com.antonkuzmn1.airplant.items;

import com.antonkuzmn1.airplant.registry.ModBlocks;
import net.minecraft.world.item.BlockItem;

public class TestItem extends BlockItem {
    public TestItem() {
        super(ModBlocks.TEST.get(), new Properties());
    }
}
