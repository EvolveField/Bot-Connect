package cn.evole.mods.mcbot.api.data;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.common.config.ModConfig;
import cn.evole.mods.mcbot.plugins.data.UserInfo;
import cn.evole.mods.mcbot.util.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
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

    public static UserInfo get(String group_id, String user_id){
        return userInfos.stream().filter(userInfo -> userInfo.getGroupId().equals(group_id) && userInfo.getQqId().equals(user_id)).findFirst().orElse(null);
    }

    public static void add(String group_id, String qq_id, String game_name){
        if (!groupHas(group_id, qq_id)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setCreateTime(System.currentTimeMillis());
            userInfo.setQqId(qq_id);
            userInfo.setGroupId(group_id);
            userInfo.setGameName(game_name);
            userInfo.setCoin(5);

            List<String> permissions = new ArrayList<>();
            for (String permission : userInfo.getPermissions()){
                permissions.add(ModConfig.get().getBotConfig().getTag() + "." + permission);//添加权限tag，区分群组服
            }
            userInfo.setPermissions(permissions);
            userInfos.add(userInfo);
        }
    }

    public static void del(String group_id, String qq_id){
        if (groupHas(group_id, qq_id)) {
            userInfos.removeIf(userInfo -> userInfo.getGroupId().equals(group_id) && userInfo.getQqId().equals(qq_id));
        }
    }

    public static void syncAdd(String group_id, String qq_id, String game_name){
        Constants.commonExecutor.submit(() -> add(group_id, qq_id, game_name));
    }

    public static void syncDel(String group_id, String qq_id){
        Constants.commonExecutor.submit(() -> del(group_id, qq_id));
    }
}
