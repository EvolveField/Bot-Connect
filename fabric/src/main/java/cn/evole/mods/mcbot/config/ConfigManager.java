package cn.evole.mods.mcbot.config;

import cn.evole.mods.mcbot.McBot;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.NodePath;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.reference.ConfigurationReference;
import org.spongepowered.configurate.reference.ValueReference;
import org.spongepowered.configurate.reference.WatchServiceListener;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

import static cn.evole.mods.mcbot.Const.LOGGER;

/**
 * @Project: McBot-fabric
 * @Author: cnlimiter
 * @CreateTime: 2024/7/31 上午12:28
 * @Description:
 */
public class ConfigManager {
    public static ConfigManager INSTANCE;

    private final ModConfig defaultConfig;

    private final ConfigurationReference<CommentedConfigurationNode> reference;
    private final ValueReference<ModConfig, CommentedConfigurationNode> configReference;

    public ConfigManager(Path configPath, WatchServiceListener listener) throws IOException {
        this.defaultConfig = new ModConfig();

        this.reference = listener.listenToConfiguration(f -> HoconConfigurationLoader.builder().path(f).build(), configPath);
        this.reference.errors().subscribe(e -> LOGGER.warn(e.getValue().getMessage(), e.getValue()));
        this.configReference = reference.referenceTo(ModConfig.class, NodePath.path(), defaultConfig);
        this.reference.save();

        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }

    public static ModConfig instance(){
        return INSTANCE.config();
    }

    public void subscribe(Consumer<ModConfig> subscriber) {
        reference.updates().subscribe(n -> subscriber.accept(config()));
    }

    public void close() {
        reference.close();
    }

    public ModConfig config() {
        ModConfig config = configReference.get();
        return config == null ? defaultConfig : config;
    }


}
