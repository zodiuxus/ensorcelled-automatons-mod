package com.zodiuxus.ensorcelledautomatons;

import net.minecraft.util.Identifier;

public class Utils {
    public static Identifier getID (String path) {
        return new Identifier(EnsorcelledAutomatons.MOD_ID, path);
    }
}
