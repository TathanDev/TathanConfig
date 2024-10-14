package fr.tathan.tathanconfig.fabric;


import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class PlatformHelperImpl {

    public static Path configPath() {
        return  FabricLoader.getInstance()
                .getConfigDir()
                .normalize();
    }
}
