package cn.evole.mods.mcbot.api.data;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.plugins.data.UserInfo;
import cn.evole.mods.mcbot.util.FileUtils;

import java.nio.file.Path;
import java.util.List;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/17 14:02
 * @Description:
 */
public class UserInfoApi {
    public static List<UserInfo> userInfos;
    public static Path userBindFile = FileUtils.checkFile(Constants.DATA_FOLDER.resolve("userBind.csv"));

    public static boolean groupHas(String group_id, String user_id){
        for (UserInfo userInfo : userInfos){
            if (userInfo.getGroupId().equals(group_id)){
                return userInfo.getQqId().equals(user_id);
            }
        }
        return false;
    }

    public static boolean isInGame(String group_id, String user_id){
        for (UserInfo userInfo : userInfos){
            if (userInfo.getGroupId().equals(group_id)){
                return userInfo.getGameName().equals(user_id);
            }
        }
        return false;
    }

    public static void add(String group_id, String qq_id, String game_name){
        if (!groupHas(group_id, qq_id)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setCreateTime(System.currentTimeMillis());
            userInfo.setQqId(qq_id);
            userInfo.setGroupId(group_id);
            userInfo.setGameName(game_name);
            userInfo.setCoin(5);
            userInfos.add(userInfo);
        }
    }

    public static void del(String group_id, String qq_id){
        if (groupHas(group_id, qq_id)) {
            userInfos.removeIf(userInfo -> userInfo.getGroupId().equals(group_id) && userInfo.getQqId().equals(qq_id));
        }
    }
}
