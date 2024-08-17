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

    public static Path checkFile(Path file) {
        if (!file.toFile().isFile()) {
            try {
                return Files.createFile(file);
            } catch (IOException ignored) {
                return file;
            }
        } else {
            return file;
        }
    }
}
