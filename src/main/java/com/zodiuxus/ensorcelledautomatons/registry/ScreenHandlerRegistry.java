package com.zodiuxus.ensorcelledautomatons.registry;

import com.zodiuxus.ensorcelledautomatons.Utils;
import com.zodiuxus.ensorcelledautomatons.guis.TableTuringScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlerRegistry {
    public static final ScreenHandlerType<? extends ScreenHandler> sct = net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry.
            registerSimple(Utils.getID("table_turing"), TableTuringScreenHandler::new);
}
