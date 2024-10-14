package fr.tathan.tathanconfig.config;

public enum ConfigFormat {
    JSON(".json"),
    TOML(".toml"),
    YAML(".yml"),
    CONF(".conf");
    private final String format;

    ConfigFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
