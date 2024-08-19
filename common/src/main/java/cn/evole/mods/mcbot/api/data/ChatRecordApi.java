package cn.evole.mods.mcbot.api.data;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.plugins.data.ChatRecord;
import cn.evole.mods.mcbot.util.FileUtils;

import java.nio.file.Path;
import java.util.Map;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/17 14:02
 * @Description:
 */
public class ChatRecordApi {
    public static Map<String, ChatRecord> chatRecords;
    public static Path chatRecordFile = FileUtils.checkFile(Constants.DATA_FOLDER.resolve("chatRecord.csv"));

    public static boolean has(String message_id){
        return chatRecords.containsKey(message_id);
    }

    public static void add(String message_id, String group_name, String qq_id, String message){
        if (!has(qq_id)) chatRecords.put(qq_id, new ChatRecord(message_id, System.currentTimeMillis(), qq_id, group_name, message));
    }
    public static void del(String message_id){
        if (has(message_id)) chatRecords.remove(message_id);
    }

    public static void syncAdd(String message_id, String group_name, String qq_id, String message){
        Constants.commonExecutor.submit(() -> add(message_id, group_name, qq_id, message));
    }

    public static void syncDel(String message_id){
        Constants.commonExecutor.submit(() -> del(message_id));
    }
}
