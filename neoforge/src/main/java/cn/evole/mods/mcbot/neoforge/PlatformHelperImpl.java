package cn.evole.mods.mcbot.neoforge;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.Optional;

public class PlatformHelperImpl {

    public static Path getGamePath() {
        return FMLPaths.GAMEDIR.get();
    }

    public static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static Optional<Path> getResourcePath(String name) {
        return Optional.ofNullable(FMLLoader.getLoadingModList().getModFileById("mcbot").getFile().findResource(name.split("/")));
    }

    public static boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }
}