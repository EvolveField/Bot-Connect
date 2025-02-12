package cn.evole.mods.mcbot;

import cn.evole.mods.mcbot.api.cmd.McBotCommandSource;
import cn.evole.mods.mcbot.util.FileUtils;
import cn.evole.onebot.client.OneBotClient;
import cn.evole.onebot.sdk.util.GsonUtils;
import com.google.gson.Gson;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Constants {

    public static final String MOD_ID = "mcbot";
    public static final String MOD_NAME = "McBot";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final ExecutorService msgExecutor = Executors.newCachedThreadPool();
    public static final ExecutorService cqExecutor = Executors.newSingleThreadExecutor();
    public static final ExecutorService commonExecutor = Executors.newFixedThreadPool(4);
    public static final Gson GSON = GsonUtils.getNullGson();
    public static Path CONFIG_FOLDER = FileUtils.checkFolder(PlatformHelper.getGamePath().resolve("mcbot"));
    public static Path APP_FOLDER = FileUtils.checkFolder(CONFIG_FOLDER.resolve("app"));
    public static Path DATA_FOLDER = FileUtils.checkFolder(CONFIG_FOLDER.resolve("data"));

    public static boolean isShutdown = false;
    public static boolean connected = false;

    public static OneBotClient onebot;
    public static MinecraftServer SERVER = null;
    public static McBotCommandSource mcBotCommand = null;

    public static final String LAGRANGE_URL = "https://github.com/LagrangeDev/Lagrange.Core/releases/download/nightly/";

    public static void shutdown(){
        cqExecutor.shutdownNow();
        msgExecutor.shutdownNow();
        commonExecutor.shutdownNow();
    }
}