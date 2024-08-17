package cn.evole.mods.mcbot.api.data;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.plugins.data.UserBind;
import cn.evole.mods.mcbot.util.FileUtils;

import java.nio.file.Path;
import java.util.List;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/17 14:02
 * @Description:
 */
public class UserBindApi {
    public static List<UserBind> userBinds;
    public static Path userBindFile = FileUtils.checkFile(Constants.dataFolder.resolve("userBind.csv"));

    public static boolean groupHas(String group_id, String user_id){
        for (UserBind userBind : userBinds){
            if (userBind.getGroupId().equals(group_id)){
                return userBind.getQqId().equals(user_id);
            }
        }
        return false;
    }

    public static boolean isInGame(String group_id, String user_id){
        for (UserBind userBind : userBinds){
            if (userBind.getGroupId().equals(group_id)){
                return userBind.getGameName().equals(user_id);
            }
        }
        return false;
    }

    public static void add(String group_id, String qq_id, String game_name){
        if (!groupHas(group_id, qq_id)) userBinds.add(new UserBind(System.currentTimeMillis(), qq_id, group_id, game_name));
    }

    public static void del(String group_id, String qq_id){
        if (groupHas(group_id, qq_id)) {
            userBinds.removeIf(userBind -> userBind.getGroupId().equals(group_id) && userBind.getQqId().equals(qq_id));
        }
    }
}
