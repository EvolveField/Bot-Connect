package cn.evole.mods.mcbot.api.config;

import java.nio.file.WatchEvent;
import java.util.function.Consumer;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/13 01:12
 * @Description:
 */
public interface AutoConfig {

    default Consumer<WatchEvent<?>> onUpdate() {
        return e -> {
        };
    }

}
