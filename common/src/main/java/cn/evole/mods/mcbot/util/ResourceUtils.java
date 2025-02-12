package cn.evole.mods.mcbot.util;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.PlatformHelper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;


/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/16 12:41
 * @Description:
 */
public class ResourceUtils {
    /**
     * 复制资源文件到指定目录。
     *
     * @param resourceName 资源文件名
     * @param targetDir    目标目录
     * @return 复制后的文件路径
     */
    public static Path copyResource(String resourceName, Path targetDir){
        boolean isDir = resourceName.endsWith("/");

        // 获取资源文件
        Optional<Path> resourceStream = PlatformHelper.getResourcePath(resourceName);

        if (resourceStream.isEmpty()) {
            Constants.LOGGER.warn("资源: {} 未找到", resourceName);
        }

        // 创建目标目录
        if (!targetDir.toFile().exists()) {
            boolean created = targetDir.toFile().mkdirs();
            if (!created) {
                Constants.LOGGER.warn("创建文件夹 : {} 失败", targetDir);
            }
        }

        // 构建目标文件路径
        Path targetFilePath = targetDir.resolve(resourceName);

        // 复制文件
        try {
            if (isDir) {
                FileUtils.copyDirectory(resourceStream.get().toFile(), targetFilePath.toFile());
            } else {
                FileUtils.copyFile(resourceStream.get().toFile(), targetFilePath.toFile(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            Constants.LOGGER.warn("复制文件: {} 失败", targetFilePath);
        }

        return targetFilePath;
    }


}
