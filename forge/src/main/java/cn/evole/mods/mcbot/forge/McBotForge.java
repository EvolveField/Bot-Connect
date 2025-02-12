package cn.evole.mods.mcbot.forge;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.McBot;
import cn.evole.mods.mcbot.api.event.server.ServerGameEvents;
import cn.evole.mods.mcbot.common.event.ICmdEvent;
import cn.evole.mods.mcbot.common.event.ITickEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod(Constants.MOD_ID)
@Mod.EventBusSubscriber
public class McBotForge {
    public McBotForge() {
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
    public static void onServerTick(TickEvent.LevelTickEvent event) {
        ITickEvent.register(event.level.getServer());
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
        ServerGameEvents.PLAYER_ADVANCEMENT.invoker().onAdvancement(event.getEntity(), event.getAdvancement());
    }

}