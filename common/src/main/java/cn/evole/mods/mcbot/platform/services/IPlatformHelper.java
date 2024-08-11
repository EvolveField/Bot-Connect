package cn.evole.mods.mcbot.platform.services;

import java.nio.file.Path;

public interface IPlatformHelper {
    Path getGamePath();

    Path getConfigPath();

    String getPlatformName();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }
}