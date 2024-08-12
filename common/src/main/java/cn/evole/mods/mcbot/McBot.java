package cn.evole.mods.mcbot;


import cn.evole.mods.mcbot.common.config.ConfigManager;
import cn.evole.mods.mcbot.core.event.IBotEvent;
import cn.evole.mods.mcbot.platform.Services;
import cn.evole.mods.mcbot.util.FileUtils;
import cn.evole.mods.mcbot.util.MsgThreadUtils;
import cn.evole.mods.mcbot.util.onebot.CQUtils;
import cn.evole.onebot.client.OneBotClient;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.configurate.reference.WatchServiceListener;

import java.io.IOException;

import static cn.evole.mods.mcbot.Constants.*;

public class McBot {

    public static void init() {
        Constants.CONFIG_FOLDER = FileUtils.checkFolder(Services.PLATFORM.getGamePath().resolve("mcbot"));
        Constants.CONFIG_FILE = Constants.CONFIG_FOLDER.resolve("config.conf");
        try {
            Constants.listener = WatchServiceListener.create();
            Constants.configManager = new ConfigManager(Constants.CONFIG_FILE, Constants.listener);
        } catch (Exception e) {
            LOGGER.error("配置加载错误...");
        }
    }


    public static void onServerStarting(MinecraftServer server) {
        SERVER = server;//获取服务器实例
    }

    public static void onServerStarted(MinecraftServer server) {
        if (configManager.config().getCommon().isAutoOpen()) {
            onebot = OneBotClient
                    .create(configManager.config().getBotConfig().build())
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
        try {
            configManager.close();
            listener.close();
        } catch (IOException e) {
            LOGGER.error("无法关闭监听配置文件进程，请手动关闭");
        }
    }
}