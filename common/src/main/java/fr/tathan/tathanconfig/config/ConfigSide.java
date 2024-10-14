package fr.tathan.tathanconfig.config;

import fr.tathan.tathanconfig.PlatformHelper;

public enum ConfigSide {
    CLIENT(PlatformHelper.configPath() + "\\client\\"),
    SERVER(PlatformHelper.configPath() +"\\server\\"),
    COMMON(PlatformHelper.configPath() + "\\");

    private final String path;

    ConfigSide(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
