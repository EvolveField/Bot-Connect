package cn.evole.mods.mcbot.plugins.data;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.api.data.ChatRecordApi;
import cn.evole.mods.mcbot.api.data.UserBindApi;
import com.github.houbb.csv.util.CsvHelper;
import com.google.common.collect.Maps;


import java.util.ArrayList;
import java.util.Map;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/17 13:30
 * @Description:
 */
public class DataHandler {
    public static void load(){
        ChatRecordApi.chatRecords = Maps.newConcurrentMap();
        UserBindApi.groupUserBinds = Maps.newConcurrentMap();
        UserBindApi.userBinds = new ArrayList<>();

        if (ChatRecordApi.chatRecordFile.toFile().exists()) {
            CsvHelper.read(Constants.dataFolder.toFile(), ChatRecord.class).forEach(chatRecord -> ChatRecordApi.chatRecords.putIfAbsent(chatRecord.getMessageId(), chatRecord));
        }

        if (UserBindApi.userBindFile.toFile().exists()){
            CsvHelper.read(Constants.dataFolder.toFile(), UserBind.class).forEach(userBind -> {
                UserBindApi.userBinds.add(userBind);
                UserBindApi.groupUserBinds.putIfAbsent(userBind.getGroupId(), Map.of(userBind.getQqId(), userBind));
            });
        }
    }


    public static void save(){
        CsvHelper.write(ChatRecordApi.chatRecords.values().stream().toList(), ChatRecordApi.chatRecordFile.toString());
        CsvHelper.write(UserBindApi.userBinds, UserBindApi.userBindFile.toString());
        clear();
    }

    public static void clear(){
        ChatRecordApi.chatRecords.clear();
        UserBindApi.groupUserBinds.clear();
        UserBindApi.userBinds.clear();
    }

    public static void reload(){
        save();
        clear();
        load();
    }
}
