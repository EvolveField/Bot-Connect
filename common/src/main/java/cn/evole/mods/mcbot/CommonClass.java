package cn.evole.mods.mcbot;


import cn.evole.mods.mcbot.config.ConfigManager;
import cn.evole.mods.mcbot.platform.Services;
import cn.evole.mods.mcbot.util.FileUtils;
import cn.evole.onebot.client.OneBotClient;
import lombok.Getter;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.configurate.reference.WatchServiceListener;

import java.nio.file.Path;

import static cn.evole.mods.mcbot.Constants.LOGGER;

public class CommonClass {
    @Getter
    public static MinecraftServer SERVER = null;
    public static Path CONFIG_FOLDER;
    public static Path CONFIG_FILE;

    public static OneBotClient onebot;

    public static ConfigManager config;
    public static WatchServiceListener listener;

    public static void init() {
        CONFIG_FOLDER = FileUtils.checkFolder(Services.PLATFORM.getGamePath().resolve("mcbot"));
        CONFIG_FILE = CONFIG_FOLDER.resolve("config.conf");
        try {
            listener = WatchServiceListener.create();
            config = new ConfigManager(CONFIG_FILE, listener);
        } catch (Exception e) {
            LOGGER.error("配置加载错误...");
        }
    }
}