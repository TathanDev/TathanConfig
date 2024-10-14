package fr.tathan.tathanconfig.config;

public class ModConfig extends BaseConfig {

    public ModConfig() {
        super("tathan-config", ConfigSide.COMMON, ConfigFormat.JSON);
    }

    @Override
    protected void addValues() {
        this.addCommentedValue("messageToShow", "The TathanConfig has been enabled", "The message shown when the config is loaded");
    }
}
