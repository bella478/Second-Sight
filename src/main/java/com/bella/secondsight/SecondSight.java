package com.bella.secondsight;

import net.fabricmc.api.ModInitializer;

public class SecondSight implements ModInitializer {

    @Override
    public void onInitialize() {
        Registers.registerAll();
    }

}
