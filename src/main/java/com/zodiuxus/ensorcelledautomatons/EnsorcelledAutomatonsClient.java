package com.zodiuxus.ensorcelledautomatons;

import com.zodiuxus.ensorcelledautomatons.registry.BlockEntityRendererRegistry;
import com.zodiuxus.ensorcelledautomatons.registry.ScreenRegistry;
import net.fabricmc.api.ClientModInitializer;

public class EnsorcelledAutomatonsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.Register();
        BlockEntityRendererRegistry.Register();
    }
}
