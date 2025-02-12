package cn.evole.mods.mcbot.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

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