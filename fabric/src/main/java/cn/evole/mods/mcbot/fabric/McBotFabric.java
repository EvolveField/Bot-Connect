package cn.evole.mods.mcbot.fabric;

import cn.evole.mods.mcbot.McBot;
import cn.evole.mods.mcbot.api.event.server.ServerGameEvents;
import cn.evole.mods.mcbot.core.event.ICmdEvent;
import cn.evole.mods.mcbot.core.event.ITickEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class McBotFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        McBot.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, commandSelection) -> ICmdEvent.register(dispatcher));
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> ServerGameEvents.PLAYER_LOGGED_IN.invoker().onPlayerLoggedIn(server, handler.player));
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> ServerGameEvents.PLAYER_LOGGED_OUT.invoker().onPlayerLoggedOut(server, handler.player));

        ServerLifecycleEvents.SERVER_STARTING.register(McBot::onServerStarting);
        ServerLifecycleEvents.SERVER_STARTED.register(McBot::onServerStarted);
        ServerLifecycleEvents.SERVER_STOPPING.register(McBot::onServerStopping);
        ServerLifecycleEvents.SERVER_STOPPED.register(McBot::onServerStopped);

        ServerTickEvents.END_SERVER_TICK.register(ITickEvent::register);
    }
}
