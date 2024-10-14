package fr.tathan.tathanconfig;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class PlatformHelper {

    @ExpectPlatform
    public static Path configPath() {
        throw new AssertionError();
    }
}
