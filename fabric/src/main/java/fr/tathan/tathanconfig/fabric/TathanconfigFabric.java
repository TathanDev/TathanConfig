package fr.tathan.tathanconfig.fabric;

import com.terraformersmc.modmenu.api.ModMenuApi;
import fr.tathan.tathanconfig.TathanConfig;
import net.fabricmc.api.ModInitializer;

public final class TathanconfigFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        TathanConfig.init();
    }
}
