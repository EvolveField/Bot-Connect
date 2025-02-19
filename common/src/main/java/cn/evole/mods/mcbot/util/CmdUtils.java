package cn.evole.mods.mcbot.util;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.api.cmd.Cmd;
import cn.evole.mods.mcbot.api.data.UserInfoApi;
import cn.evole.mods.mcbot.common.config.ModConfig;
import cn.evole.mods.mcbot.plugins.cmd.CmdHandler;
import cn.evole.onebot.sdk.event.message.GroupMessageEvent;
import com.google.common.collect.Maps;
import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/16 23:38
 * @Description:
 */
public class CmdUtils {

    public static final Map<String, String> VARS = Maps.newConcurrentMap();

    private final static String VAR_REGEX = "(%(\\w+)+%)+";
    public static String findSimpleCmd(String command) {
        // 找到最后一个空格的位置
        int lastSpaceIndex = command.lastIndexOf(" ");
        // 如果没有空格，则整个命令就是关键词
        if (lastSpaceIndex == -1) {
            return command;
        }
        // 返回最后一个空格之前的内容
        return command.substring(0, lastSpaceIndex);
    }

    public static boolean hasPermission(String group_id, String user_id, Cmd cmd){
        if (cmd.getId().equals("bind")) return true;
        else return  (UserInfoApi.get(group_id, user_id) != null &&
                UserInfoApi.get(group_id, user_id).getPermissions().contains(ModConfig.get().getBotConfig().getTag().getValue() + ".mcbot.cmd." + cmd.getId())
                || cmd.getAllow_members().contains(user_id)
        );
    }

    /**
     *
     * @param event 消息事件
     * @return 是否是管理员
     */
    public static boolean groupAdminParse(GroupMessageEvent event) {
        return !event.getSender().getRole().equals("MEMBER") && !event.getSender().getRole().equals("member");
    }

    /**
     * 变量解析
     *
     * @param event 消息事件
     * @param cmd   q群中指令
     * @return 处理完的指令
     */
    public static Cmd varParse(GroupMessageEvent event, String cmd) {
        VARS.put("user_id", event.getSender().getUserId());//初始化变量列表
        VARS.put("group_id", String.valueOf(event.getGroupId()));
        VARS.put("user_age", String.valueOf(event.getSender().getAge()));
        VARS.put("user_nickname", String.valueOf(event.getSender().getNickname()));

        String cmdStart = cmd.split(" ")[0];//部分指令头

        if (cmdStart.isEmpty()) return null;

        Cmd selectCmd = null;
        boolean useCmd = false;

        for (Cmd cmd2 : CmdHandler.cmds.values()){//将含有昵称的指令替换为源命令
            if (cmd2.getId().equals(cmdStart) || cmd2.getCmd().contains(cmdStart)) {
                useCmd = true;//如果部分指令头等于id / 源命令包含部分指令头，则可以视为使用该源命令
            } else {
                for (String alies : cmd2.getAlies()){
                    if (cmd.contains(alies)) {
                        useCmd = true;//如果指令包含该Cmd中的任意一个alie，则可以视为使用该源命令
                        break;//跳出循环
                    }
                }
            }
            if (useCmd) {
                selectCmd = cmd2;
                break;//跳出循环
            }
        }

        if (selectCmd != null) {
            String innerParse = innerVarParse(selectCmd.getCmd(), VARS);
            List<String> cmdSplits = new ArrayList<>(Arrays.asList(cmd.split(" ")));//拆分命令
            if (cmdSplits.size() > 1) {
                for (String key : cmdSplits){
                    if (key.equals(selectCmd.getId())){
                        cmdSplits.remove(key);
                    }
                }
            }
            String outerParse = outerVarParse(innerParse, cmdSplits);
            //拼接回命令
            return new Cmd(selectCmd.getId(), outerParse, selectCmd.getAlies(), selectCmd.getAllow_members(), selectCmd.getPermission(), selectCmd.getAfter_cmds(), selectCmd.getAnswer(), selectCmd.isEnable());
        } else {
            return null;//如果最终命令为空则返回null
        }
    }

    public static String innerVarParse(String command, Map<String, String> variables) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            command = command.replace("%" + key + "%", value);
        }
        return command;
    }

    public static String outerVarParse(String command, List<String> values) {
        StringBuilder result = new StringBuilder();
        int valueIndex = 0;

        for (int i = 0; i < command.length(); i++) {
            char currentChar = command.charAt(i);

            if (currentChar == '%') {
                    // 如果已经在一个占位符中，替换为指定的值
                    if (valueIndex < values.size()) {
                        result.append(values.get(valueIndex));
                        valueIndex++;
                    }
            } else {
                    result.append(currentChar);
            }
        }
        return result.toString();
    }
}
