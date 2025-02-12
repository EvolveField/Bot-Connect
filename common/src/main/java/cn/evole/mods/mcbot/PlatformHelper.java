package cn.evole.mods.mcbot;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;
import java.util.Optional;

public class PlatformHelper {
    @ExpectPlatform
    public static Path getGamePath() {
        throw new UnsupportedOperationException();
    }

    @ExpectPlatform
    public static Path getConfigPath() {
        throw new UnsupportedOperationException();
    }

    @ExpectPlatform
    public static Optional<Path> getResourcePath(String name) {
        throw new UnsupportedOperationException();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String modId) {
        throw new UnsupportedOperationException();
    }

}