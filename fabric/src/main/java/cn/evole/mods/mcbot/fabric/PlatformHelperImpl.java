package cn.evole.mods.mcbot.fabric;

import cn.evole.mods.mcbot.Constants;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;
import java.util.Optional;

public class PlatformHelperImpl{

    public static Path getGamePath() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static Optional<Path> getResourcePath(String name) {
        return FabricLoader.getInstance().getModContainer(Constants.MOD_ID).orElseThrow(null).findPath("/" + name);
    }

    public static boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

}
