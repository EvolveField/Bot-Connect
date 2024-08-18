package cn.evole.mods.mcbot.plugins.cmd;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.api.cmd.Cmd;
import com.google.common.base.Stopwatch;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import lombok.val;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static cn.evole.mods.mcbot.Constants.GSON;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/14 13:19
 * @Description:
 */
public class CmdHandler {
    private static final File dir = Constants.CONFIG_FOLDER.resolve("cmds").toFile();

    public static Map<String, Cmd> cmds = new ConcurrentHashMap<>();

    public static void load() {
        val stopwatch = Stopwatch.createStarted();

        writeDefault();
        clear();

        if (!dir.mkdirs() && dir.isDirectory()) {
            loadFiles();
        }

        stopwatch.stop();

        Constants.LOGGER.info("加载 {} 个自定义命令，耗时 {} 毫秒", cmds.size(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void writeDefault() {
        if (!dir.exists() && dir.mkdirs()) {
            JsonObject json1 = GSON.fromJson("{'id': 'list', 'cmd': 'list', 'alies': ['服务器在线'], 'allow_members': [], permission: 'ALL', 'after_cmds': [], 'answer': 'NO', 'enable': true}", JsonObject.class);
            JsonObject json2 = GSON.fromJson("{'id': 'say', 'cmd': 'say %', 'alies': ['转发'], 'allow_members': [], permission: 'OP', 'after_cmds': [], 'answer': '转发成功！', 'enable': true}", JsonObject.class);
            JsonObject json3 = GSON.fromJson("{'id': 'bind', 'cmd': 'mcbot addBind %', 'alies': ['绑定'], 'allow_members': [], permission: 'ALL', 'after_cmds': [], 'answer': '绑定 % 成功！', 'enable': true}", JsonObject.class);

            // 尝试创建和写入文件
            try (FileWriter writerList = new FileWriter(new File(dir, "list.json"));
                 FileWriter writerSay = new FileWriter(new File(dir, "say.json"));
                 FileWriter writerBind = new FileWriter(new File(dir, "bind.json"))) {

                GSON.toJson(json1, writerList);
                GSON.toJson(json2, writerSay);
                GSON.toJson(json3, writerBind);

            } catch (IOException e) {
                Constants.LOGGER.error("生成默认自定义命令时出错", e);
            }
        }
    }

    private static void loadFiles() {
        val files = dir.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json"));
        if (files == null) {
            Constants.LOGGER.warn("目录 {} 不存在或无法访问", dir.getAbsolutePath());
            return;
        }

        for (File file : files) {
            try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8)) {
                Cmd customCmd = GSON.fromJson(reader, Cmd.class);

                if (customCmd != null && customCmd.isEnable()) {
                    String id = customCmd.getId();
                    Constants.LOGGER.debug("开始处理文件: {}", file.getName());
                    cmds.put(id, customCmd);
                    Constants.LOGGER.debug("成功加载命令: {}", id);
                }
            } catch (IOException e) {
                Constants.LOGGER.error("读取文件 {} 出错", file.getName(), e);
            } catch (JsonParseException e) {
                Constants.LOGGER.error("解析 JSON 文件 {} 出错", file.getName(), e);
            } catch (Exception e) {
                Constants.LOGGER.error("加载自定义命令出错，请检查文件 {}", file.getName(), e);
            }
        }
    }

    public static void clear() {
        cmds.clear();
    }
}
