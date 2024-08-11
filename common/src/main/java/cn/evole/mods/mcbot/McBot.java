package cn.evole.mods.mcbot;


import cn.evole.mods.mcbot.common.config.ConfigManager;
import cn.evole.mods.mcbot.platform.Services;
import cn.evole.mods.mcbot.util.FileUtils;
import org.spongepowered.configurate.reference.WatchServiceListener;

import static cn.evole.mods.mcbot.Constants.LOGGER;

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
}