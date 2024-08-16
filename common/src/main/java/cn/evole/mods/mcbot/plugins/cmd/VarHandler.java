//package cn.evole.mods.mcbot.plugins.cmd;
//
//import cn.evole.mods.mcbot.Constants;
//import com.google.common.base.Stopwatch;
//import com.google.gson.JsonObject;
//import lombok.val;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.TimeUnit;
//
//import static cn.evole.mods.mcbot.Constants.GSON;
//
///**
// * @Project: McBot
// * @Author: cnlimiter
// * @CreateTime: 2024/8/17 00:08
// * @Description:
// */
//public class VarHandler {
//    private static final File dir = Constants.CONFIG_FOLDER.toFile();
//
//    private static Map<String, String> vars = new ConcurrentHashMap<>();
//
//    public static void load() {
//        val stopwatch = Stopwatch.createStarted();
//
//        writeDefault();
//        clear();
//
//        if (!dir.mkdirs() && dir.isDirectory()) {
//            loadFiles();
//        }
//
//        stopwatch.stop();
//
//        Constants.LOGGER.info("加载 {} 个变量，耗时 {} 毫秒", vars.size(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
//    }
//
//    private static void writeDefault() {
//        if (!dir.exists() && dir.mkdirs()) {
//            JsonObject json1 = GSON.fromJson("{'player': 'list', 'cmd': 'list', 'alies': ['服务器在线'], 'allow_members': [], permission: 'ALL', 'enable': true}", JsonObject.class);
//
//            // 尝试创建和写入文件
//            try (FileWriter writerList = new FileWriter(new File(dir, "vars.json"))
//                 ) {
//                GSON.toJson(json1, writerList);
//            } catch (IOException e) {
//                Constants.LOGGER.error("生成默认变量时出错", e);
//            }
//        }
//    }
//}
