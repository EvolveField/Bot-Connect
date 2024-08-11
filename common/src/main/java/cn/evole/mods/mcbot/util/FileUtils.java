package cn.evole.mods.mcbot.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/11 20:18
 * @Description:
 */
public class FileUtils {
    public static Path checkFolder(Path folder) {
        if (!folder.toFile().isDirectory()) {
            try {
                return Files.createDirectories(folder);
            } catch (IOException ignored) {
                return folder;
            }
        } else {
            return folder;
        }
    }
}
