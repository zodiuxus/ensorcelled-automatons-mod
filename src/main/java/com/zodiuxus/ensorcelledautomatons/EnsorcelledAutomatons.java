package com.zodiuxus.ensorcelledautomatons;

import com.zodiuxus.ensorcelledautomatons.registry.ModBlocks;
import com.zodiuxus.ensorcelledautomatons.registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class EnsorcelledAutomatons implements ModInitializer {
    // Basic mod ID, the unique identifier for the game where it shows up as "ensaut:mod_item_name"
    public static final String MOD_ID = "ensaut";

    // Instead of getting lost in onInitialize() on every .java file, I'd rather have them
    // all combined into a single one so I can easily find what I need
    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
    }
}
