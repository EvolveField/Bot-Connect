package cn.evole.mods.mcbot.platform;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;
import java.util.Optional;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public Path getGamePath() {
        return FabricLoader.getInstance().getGameDir();
    }

    @Override
    public Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public Optional<Path> getResourcePath(String name) {
        return FabricLoader.getInstance().getModContainer(Constants.MOD_ID).orElseThrow(null).findPath("/" + name);
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
