package fr.tathan.tathanconfig.neoforge;

import fr.tathan.tathanconfig.TathanConfig;
import fr.tathan.tathanconfig.screen.ConfigScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(TathanConfig.MOD_ID)
public final class TathanconfigNeoForge {
    public TathanconfigNeoForge(ModContainer container) {
        TathanConfig.init();

        if (FMLEnvironment.dist.isClient())
        {
            container.registerExtensionPoint(IConfigScreenFactory.class, ((modContainer, parent) -> new ConfigScreen(parent, Component.literal("Tathan Config"), TathanConfig.CONFIG)));
        }


    }
}
