package cn.evole.mods.mcbot.api.cmd;

import cn.evole.mods.mcbot.api.data.UserInfoApi;
import cn.evole.mods.mcbot.util.CmdUtils;
import cn.evole.onebot.sdk.event.message.GroupMessageEvent;
import lombok.val;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/12 01:56
 * @Description:
 */
public class CmdApi {


    public static void invokeCommandGroup(GroupMessageEvent event, String msg) {
        String originCmd = msg.substring(1);//去除前缀

        val user_id = String.valueOf(event.getUserId());
        val group_id = String.valueOf(event.getGroupId());

        val cmd = CmdUtils.varParse(event, originCmd);


        if (UserInfoApi.groupHas(group_id, user_id)){

        }





    }
}
