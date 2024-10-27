package cn.evole.mods.mcbot.util.onebot;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.util.CompressUtils;
import cn.evole.mods.mcbot.util.FileUtils;
import cn.evole.mods.mcbot.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.apache.commons.codec.binary.Hex.DEFAULT_CHARSET;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/10/27 19:57
 * @Description:
 */
public class AppHandler {
    public static void init(){
        FileUtils.setResDirPath(Constants.APP_FOLDER);
        final var osName = System.getProperty("os.name", "");
        var url = "";
        var fileName = "";
        if (osName.startsWith("Mac OS")) {
            fileName = "Lagrange.OneBot_osx-x64_net8.0_SelfContained.tar.gz";
        } else if (osName.startsWith("Windows")) {
            fileName = "Lagrange.OneBot_win-x64_net8.0_SelfContained.zip";
        } else {
            fileName = "Lagrange.OneBot_linux-musl-x64_net8.0_SelfContained.tar.gz";
        }
        url = Constants.LAGRANGE_URL + fileName;
        var app = new OnebotApp(fileName, url);
        app.download();
        //根据文件名后缀（.tar.gz/.zip）解压缩 fileName
        try {
            if (osName.startsWith("Mac OS")) {
                CompressUtils.tarGzipDecompression(fileName, Constants.APP_FOLDER.toString());
            } else if (osName.startsWith("Windows")) {
                CompressUtils.zipDecompression(fileName, Constants.APP_FOLDER.toString());
            } else {
                CompressUtils.tarGzipDecompression(fileName, Constants.APP_FOLDER.toString());
            }
        } catch (IOException e) {
            Constants.LOGGER.error("解压失败", e);
        }
        ResourceUtils.copyResource("config/appsettings.json", Constants.APP_FOLDER.resolve("Lagrange.OneBot"));
        if (osName.startsWith("Windows")) {
            openExe(Constants.APP_FOLDER.resolve("Lagrange.OneBot").resolve("Lagrange.OneBot.exe").toFile().getAbsolutePath());
        } else {
            executeShellCmd("./" + Constants.APP_FOLDER.resolve("Lagrange.OneBot").resolve("Lagrange.OneBot").toFile().getAbsolutePath());
        }
    }


    public static void openExe(String cmd) {
        BufferedReader br = null;
        BufferedReader brError = null;

        try {
            //执行exe  cmd可以为字符串(exe存放路径)也可为数组，调用exe时需要传入参数时，可以传数组调用(参数有顺序要求)
            Process p = Runtime.getRuntime().exec(cmd);
            String line = null;
            //获得子进程的输入流。
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //获得子进程的错误流。
            brError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = br.readLine()) != null  || (line = brError.readLine()) != null) {
                //输出exe输出的信息以及错误信息
                System.out.println(line);
            }
        } catch (Exception e) {
            Constants.LOGGER.error(e.getLocalizedMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    Constants.LOGGER.error(e.getLocalizedMessage());
                }
            }
        }
    }

    public static void executeShellCmd(String commandString) {
        String[] command = {"/bin/sh", "-c", commandString};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // 将错误输出和标准输出合并
        try {
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), DEFAULT_CHARSET))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            int exitCode = process.waitFor(); // 等待进程结束
            Constants.LOGGER.error("退出码: {}", exitCode);
        } catch (IOException | InterruptedException e) {
            Constants.LOGGER.error("执行命令时出错 {}", e.getLocalizedMessage());
        }
    }
}
