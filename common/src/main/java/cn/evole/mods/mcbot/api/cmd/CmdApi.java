package cn.evole.mods.mcbot.api.cmd;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.api.bot.BotApi;
import cn.evole.mods.mcbot.plugins.cmd.CmdHandler;
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


    public static void invokeGroupCommand(GroupMessageEvent event, String msg) {
        String originCmd = msg.substring(1);//去除前缀

        val user_id = String.valueOf(event.getUserId());
        val group_id = String.valueOf(event.getGroupId());

        val cmd = CmdUtils.varParse(event, originCmd);

        if (cmd == null) return;

        if (CmdUtils.groupAdminParse(event)) {
            //Constants.LOGGER.info(cmd.getCmd());
            BotApi.sendGroupMsg(event.getGroupId(), Constants.mcBotCommand.runCommand(cmd.getCmd()));//执行指令
            if (!cmd.getAfter_cmds().isEmpty())
                cmd.getAfter_cmds().forEach(s -> BotApi.sendGroupMsg(event.getGroupId(), Constants.mcBotCommand.runCommand(s)));
        } else if (CmdUtils.hasPermission(group_id, user_id, cmd)) {
            BotApi.sendGroupMsg(event.getGroupId(), Constants.mcBotCommand.runCommand(cmd.getCmd()));//执行指令
            if (!cmd.getAfter_cmds().isEmpty()) {//连续指令是否为空
                cmd.getAfter_cmds().forEach(s -> {
                    if (CmdUtils.hasPermission(group_id, user_id, CmdHandler.cmds.get(s))) {//再次检测下条指令是否有权限
                        BotApi.sendGroupMsg(event.getGroupId(), Constants.mcBotCommand.runCommand(s));
                    }
                });
            }
        }
    }
}
