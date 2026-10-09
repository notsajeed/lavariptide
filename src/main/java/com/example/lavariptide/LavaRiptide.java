package com.example.lavariptide;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LavaRiptide implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("lavariptide");

    @Override
    public void onInitialize() {
        LOGGER.info("Lava Riptide loaded: riptide tridents now work in lava.");
    }
}
