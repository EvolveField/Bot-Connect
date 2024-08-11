package cn.evole.mods.mcbot;

import net.fabricmc.api.ModInitializer;

public class McBotFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        McBot.init();
    }
}
