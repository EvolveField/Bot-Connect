package cn.evole.mods.mcbot.neoforge;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.McBot;
import cn.evole.mods.mcbot.api.event.server.ServerGameEvents;
import cn.evole.mods.mcbot.common.event.ICmdEvent;
import cn.evole.mods.mcbot.common.event.ITickEvent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import org.jetbrains.annotations.NotNull;

@Mod(Constants.MOD_ID)
@EventBusSubscriber
public class McBotNeoForge {
    public McBotNeoForge(IEventBus eventBus) {
        McBot.init();
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        McBot.onServerStarting(event.getServer());
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        McBot.onServerStarted(event.getServer());
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        McBot.onServerStopping(event.getServer());
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        McBot.onServerStopped(event.getServer());
    }

    @SubscribeEvent
    public static void onServerTick(LevelTickEvent.Post event) {
        ITickEvent.register(event.getLevel().getServer());
    }

    @SubscribeEvent
    public static void cmdRegister(@NotNull RegisterCommandsEvent event) {
        ICmdEvent.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerJoin(@NotNull PlayerEvent.PlayerLoggedInEvent event) {
        ServerGameEvents.PLAYER_LOGGED_IN.invoker().onPlayerLoggedIn(event.getEntity().getServer(), (ServerPlayer) event.getEntity());
    }
    @SubscribeEvent
    public static void onPlayerLeave(@NotNull PlayerEvent.PlayerLoggedOutEvent event) {
        ServerGameEvents.PLAYER_LOGGED_OUT.invoker().onPlayerLoggedOut(event.getEntity().getServer(), (ServerPlayer) event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerDeath(@NotNull LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer)
            ServerGameEvents.PLAYER_DEATH.invoker().onDeath(event.getSource(), serverPlayer);
    }

    @SubscribeEvent
    public static void onPlayerChat(@NotNull ServerChatEvent event) {
        ServerGameEvents.SERVER_CHAT.invoker().onChat(event.getPlayer(), event.getRawText());
    }

    @SubscribeEvent
    public static void onAdvancement(AdvancementEvent.AdvancementEarnEvent event) {
        ServerGameEvents.PLAYER_ADVANCEMENT.invoker().onAdvancement(event.getEntity(), event.getAdvancement().value());
    }

}