package fr.tathan.tathanconfig.fabric.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.gui.ModsScreen;
import fr.tathan.tathanconfig.TathanConfig;
import fr.tathan.tathanconfig.screen.ConfigScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModMenuConfig implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (screen) -> {
            return new  ConfigScreen(screen, Component.literal("Tathan Config"), TathanConfig.CONFIG);
        };
    }

}
