package cn.evole.mods.mcbot;

import cn.evole.mods.mcbot.common.config.ConfigManager;
import cn.evole.onebot.client.OneBotClient;
import lombok.Getter;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.configurate.reference.WatchServiceListener;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Constants {

	public static final String MOD_ID = "mcbot";
	public static final String MOD_NAME = "McBot";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static final ExecutorService msgExecutor = Executors.newCachedThreadPool();
	public static boolean isShutdown = false;
	public static boolean connected = false;

	public static Path CONFIG_FILE;
	public static Path CONFIG_FOLDER;
	public static OneBotClient onebot;
	public static ConfigManager configManager;
	public static WatchServiceListener listener;
	public static MinecraftServer SERVER = null;
}