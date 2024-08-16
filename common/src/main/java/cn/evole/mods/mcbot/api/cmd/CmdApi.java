package cn.evole.mods.mcbot.api.cmd;

import cn.evole.mods.mcbot.util.CmdUtils;
import cn.evole.onebot.sdk.event.message.GroupMessageEvent;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/12 01:56
 * @Description:
 */
public class CmdApi {


    public static void invokeCommandGroup(GroupMessageEvent event, String msg) {
        String command = msg.substring(1);//去除前缀
        String originCommand = command;
        command = CmdUtils.cmdParse(command);





    }
}
