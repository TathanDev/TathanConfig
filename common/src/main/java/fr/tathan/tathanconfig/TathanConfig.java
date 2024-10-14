package fr.tathan.tathanconfig;

import fr.tathan.tathanconfig.config.BaseConfig;
import fr.tathan.tathanconfig.config.ModConfig;
import org. slf4j. Logger;
import org.slf4j.LoggerFactory;

public final class TathanConfig {
    public static final String MOD_ID = "tathanconfig";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static BaseConfig CONFIG = new ModConfig();

    public static void init() {
        CONFIG.load();
        LOGGER.info("Test Value {}", CONFIG.getCommentedValue("messageToShow").toString());

    }
}
