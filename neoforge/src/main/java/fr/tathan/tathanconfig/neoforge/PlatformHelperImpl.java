package fr.tathan.tathanconfig.neoforge;


import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class PlatformHelperImpl {

    public static Path configPath() {
        return FMLPaths.CONFIGDIR.get();
    }
}
