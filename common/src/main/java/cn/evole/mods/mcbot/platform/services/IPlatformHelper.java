package cn.evole.mods.mcbot.platform.services;

import java.nio.file.Path;
import java.util.Optional;

public interface IPlatformHelper {
    Path getGamePath();

    Path getConfigPath();

    String getPlatformName();

    Optional<Path> getResourcePath(String name);

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }
}