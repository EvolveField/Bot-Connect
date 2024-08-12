package cn.evole.mods.mcbot;


import cn.evole.mods.mcbot.api.config.ConfigManager;
import cn.evole.mods.mcbot.common.config.*;
import cn.evole.mods.mcbot.core.event.IBotEvent;
import cn.evole.mods.mcbot.util.MsgThreadUtils;
import cn.evole.mods.mcbot.util.onebot.CQUtils;
import cn.evole.onebot.client.OneBotClient;
import net.minecraft.server.MinecraftServer;

import static cn.evole.mods.mcbot.Constants.*;

public class McBot {

    public static void init() {
        try (final ConfigManager manager = new ConfigManager()) {
            manager.initConfigs(
                    ModConfig.class
            );
        } catch (Exception e) {
            LOGGER.error("配置加载错误...");
        }
    }


    public static void onServerStarting(MinecraftServer server) {
        SERVER = server;//获取服务器实例
    }

    public static void onServerStarted(MinecraftServer server) {
        if (ModConfig.get().getCommon().isAutoOpen()) {
            onebot = OneBotClient
                    .create(ModConfig.get().getBotConfig().build())
                    .open()
                    .registerEvents(new IBotEvent());
            connected = true;
        }
        //CustomCmdHandler.INSTANCE.load();//自定义命令加载
        //keepAlive = new KeepAlive();
        //Const.messageThread.register(keepAlive::register);
    }

    public static void onServerStopping(MinecraftServer server) {
        isShutdown = true;
        LOGGER.info("▌ §c正在关闭群服互联");
        //UserBindApi.save(CONFIG_FOLDER);
        //ChatRecordApi.save(CONFIG_FOLDER);
        //CustomCmdHandler.INSTANCE.clear();//自定义命令持久层清空
    }

    public static void onServerStopped(MinecraftServer server) {
        CQUtils.shutdown();
        MsgThreadUtils.shutdown();
        if (onebot != null) onebot.close();
    }
}