package cn.evole.mods.mcbot.api.config;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.platform.Services;
import lombok.Getter;
import net.minecraft.client.gui.screens.Screen;
import org.apache.commons.lang3.function.TriFunction;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.reference.ConfigurationReference;
import org.spongepowered.configurate.reference.ValueReference;
import org.spongepowered.configurate.reference.WatchServiceListener;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/13 01:11
 * @Description:
 */
public final class ConfigHandler<T> implements AutoCloseable {

    private final Class<T> clazz;
    private final Path configFile;
    private WatchServiceListener listener;
    @Getter
    private ConfigurationReference<CommentedConfigurationNode> base;
    @Getter
    private ValueReference<T, CommentedConfigurationNode> baseConfig;

    public ConfigHandler(final Path applicationFolder, final String configName, final Class<T> clazz) {
        this.clazz = clazz;
        this.configFile = applicationFolder.resolve(configName);

        try {
            this.listener = WatchServiceListener.create();
            this.base = this.listener.listenToConfiguration(file ->
                            YamlConfigurationLoader.builder()
                                    .defaultOptions(
                                            opts -> opts.shouldCopyDefaults(true)
                                    )
                                    .path(file)
                                    .nodeStyle(NodeStyle.BLOCK)
                                    .indent(2)
                                    .build()
                    , configFile);

            this.listener.listenToFile(configFile, event -> {
                Constants.LOGGER.info("Updated ConfigFile {}/{}", applicationFolder.getFileName().toString(), configFile.getFileName().toString());
                if (getConfig() instanceof AutoConfig conf) {
                    conf.onUpdate().accept(event);
                }
            });

            this.baseConfig = this.base.referenceTo(clazz);
            this.base.saveAsync();

        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

    public T getConfig() {
        return baseConfig.get();
    }

    public void saveToFile() throws ConfigurateException {
        this.base.node().set(clazz, clazz.cast(getConfig()));
        this.base.loader().save(this.base.node());
    }

    public Screen createConfigScreen(Screen parent, TriFunction<Screen, T, Consumer<T>, Screen> screenFunction) {
        if (Services.PLATFORM.isModLoaded("cloth-config2")) {
            return screenFunction.apply(parent, getConfig(), baseConfig::setAndSaveAsync);
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        try {
            this.listener.close();
            this.base.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}