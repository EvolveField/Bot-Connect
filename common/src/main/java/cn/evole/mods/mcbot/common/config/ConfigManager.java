package cn.evole.mods.mcbot.common.config;

import cn.evole.mods.mcbot.Constants;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.reference.ConfigurationReference;
import org.spongepowered.configurate.reference.ValueReference;
import org.spongepowered.configurate.reference.WatchServiceListener;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/11 19:16
 * @Description:
 */
public class ConfigManager {
    private final ModConfig defaultConfig;

    private final ConfigurationReference<CommentedConfigurationNode> base;
    private final ValueReference<ModConfig, CommentedConfigurationNode> configReference;

    public ConfigManager(Path configPath, WatchServiceListener listener) throws IOException {
        this.defaultConfig = new ModConfig();

        this.base = listener.listenToConfiguration(f -> HoconConfigurationLoader.builder().path(f).build(), configPath);
        this.base.updates().subscribe(e -> Constants.LOGGER.info("Configuration auto-reloaded"));
        this.base.errors().subscribe(e -> Constants.LOGGER.warn(e.getValue().getMessage(), e.getValue()));
        this.configReference = base.referenceTo(ModConfig.class);
        this.base.saveAsync();
    }

    public void subscribe(Consumer<ModConfig> subscriber) {
        base.updates().subscribe(n -> subscriber.accept(config()));
    }
    public void close() throws ConfigurateException {
        base.close();
    }

    public ModConfig config() {
        ModConfig config = configReference.get();
        return config == null ? defaultConfig : config;
    }
}
