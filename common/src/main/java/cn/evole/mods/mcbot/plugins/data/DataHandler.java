package cn.evole.mods.mcbot.plugins.data;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.api.data.ChatRecordApi;
import cn.evole.mods.mcbot.api.data.UserInfoApi;
import com.github.houbb.csv.util.CsvHelper;
import com.google.common.collect.Maps;

import java.util.ArrayList;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/17 13:30
 * @Description:
 */
public class DataHandler {
    public static void load(){
        ChatRecordApi.chatRecords = Maps.newConcurrentMap();
        UserInfoApi.userInfos = new ArrayList<>();

        if (ChatRecordApi.chatRecordFile.toFile().exists()) {
            CsvHelper.read(Constants.DATA_FOLDER.toFile(), ChatRecord.class).forEach(chatRecord -> ChatRecordApi.chatRecords.putIfAbsent(chatRecord.getMessageId(), chatRecord));
        }

        if (UserInfoApi.userBindFile.toFile().exists()){
            UserInfoApi.userInfos.addAll(CsvHelper.read(Constants.DATA_FOLDER.toFile(), UserInfo.class));
        }
    }


    public static void save(){
        CsvHelper.write(ChatRecordApi.chatRecords.values().stream().toList(), ChatRecordApi.chatRecordFile.toString());
        CsvHelper.write(UserInfoApi.userInfos, UserInfoApi.userBindFile.toString());
        clear();
    }

    public static void clear(){
        ChatRecordApi.chatRecords.clear();
        UserInfoApi.userInfos.clear();
    }

    public static void reload(){
        save();
        clear();
        load();
    }
}
