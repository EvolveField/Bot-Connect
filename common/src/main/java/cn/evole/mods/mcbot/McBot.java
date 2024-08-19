package cn.evole.mods.mcbot;


import cn.evole.mods.mcbot.api.cmd.McBotCommandSource;
import cn.evole.mods.mcbot.api.config.ConfigManager;
import cn.evole.mods.mcbot.api.event.server.ServerGameEvents;
import cn.evole.mods.mcbot.common.config.*;
import cn.evole.mods.mcbot.plugins.cmd.CmdHandler;
import cn.evole.mods.mcbot.common.event.IBotEvent;
import cn.evole.mods.mcbot.common.event.IChatEvent;
import cn.evole.mods.mcbot.common.event.IPlayerEvent;
import cn.evole.mods.mcbot.plugins.data.DataHandler;
import cn.evole.mods.mcbot.util.locale.I18n;
import cn.evole.onebot.client.OneBotClient;
import net.minecraft.server.MinecraftServer;

import static cn.evole.mods.mcbot.Constants.*;

public class McBot {

    public static void init() {
        try (final ConfigManager manager = new ConfigManager(CONFIG_FOLDER)) {
            manager.initConfigs(
                    ModConfig.class
            );
        } catch (Exception e) {
            LOGGER.error("配置加载错误...");
        }

        ServerGameEvents.PLAYER_LOGGED_IN.register((server, player) -> IPlayerEvent.loggedIn(player.level(), player));
        ServerGameEvents.PLAYER_LOGGED_OUT.register((server, player) -> IPlayerEvent.loggedOut(player.level(), player));
        ServerGameEvents.PLAYER_ADVANCEMENT.register(IPlayerEvent::advancement);
        ServerGameEvents.PLAYER_DEATH.register(IPlayerEvent::death);
        ServerGameEvents.SERVER_CHAT.register(IChatEvent::register);
    }


    public static void onServerStarting(MinecraftServer server) {
        SERVER = server;//获取服务器实例
        I18n.init();
        commonExecutor.submit(CmdHandler::load);//自定义命令加载
        commonExecutor.submit(DataHandler::load);//数据加载

    }

    public static void onServerStarted(MinecraftServer server) {
        mcBotCommand = new McBotCommandSource(server);
        if (ModConfig.get().getCommon().isAutoOpen()) {
            onebot = OneBotClient
                    .create(ModConfig.get().getBotConfig().build())
                    .open()
                    .registerEvents(new IBotEvent());
            connected = true;
        }
    }

    public static void onServerStopping(MinecraftServer server) {
        isShutdown = true;
        LOGGER.info("▌ §c正在关闭群服互联");
        commonExecutor.submit(CmdHandler::clear);//自定义命令持久层清空
        commonExecutor.submit(DataHandler::save);//数据储存
    }

    public static void onServerStopped(MinecraftServer server) {
        ConfigManager.getInstance().saveAllConfig();
        Constants.shutdown();
        if (onebot != null) onebot.close();
    }
}