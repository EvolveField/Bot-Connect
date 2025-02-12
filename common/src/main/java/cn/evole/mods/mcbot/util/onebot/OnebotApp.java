package cn.evole.mods.mcbot.util.onebot;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.util.DownloadUtils;
import cn.evole.mods.mcbot.util.FileUtils;
import lombok.Getter;

import java.nio.file.Path;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/10/27 19:49
 * @Description:
 */
public class OnebotApp {
    @Getter
    private final String filename;
    private final Path filePath;
    private final String url;

    public OnebotApp(String filename, String url) {
        this.filename = filename;
        this.url = url;
        this.filePath = FileUtils.getResPackPath(filename);
    }

    public void download(){
        try {
            DownloadUtils.download(url, filePath);
        } catch (Exception e) {
            Constants.LOGGER.warn("Error while downloading: %s", e);
        }
    }
}
