package com.zodiuxus.ensorcelledautomatons.registry;

import com.zodiuxus.ensorcelledautomatons.guis.TuringTableScreen;

public class ScreenRegistry {

    public static void Register(){
        net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry.register(ScreenHandlerRegistry.sct, TuringTableScreen::new);
    }
}
