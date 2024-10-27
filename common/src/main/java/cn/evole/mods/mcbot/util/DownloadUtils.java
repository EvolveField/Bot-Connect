package cn.evole.mods.mcbot.util;

import cn.evole.mods.mcbot.Constants;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/10/27 19:47
 * @Description:
 */
public class DownloadUtils {

    public static void download(String url, Path localFile) throws IOException, URISyntaxException {
        Constants.LOGGER.info("Downloading: {} -> {}", url, localFile);
        FileUtils.copyURLToFile(new URI(url).toURL(), localFile.toFile(),
                (int) TimeUnit.SECONDS.toMillis(3), (int) TimeUnit.SECONDS.toMillis(33));
        Constants.LOGGER.debug("Downloaded: {} -> {}", url, localFile);
    }
}
