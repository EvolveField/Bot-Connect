package cn.evole.mods.mcbot.util;

import cn.evole.mods.mcbot.api.cmd.Cmd;
import cn.evole.mods.mcbot.plugins.cmd.CmdHandler;
import cn.evole.onebot.sdk.event.message.GroupMessageEvent;
import com.google.common.collect.Maps;
import lombok.val;

import java.util.Map;
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
    private final static String VAR_REGEX = "^(%(\\w+)+%)+";
    public static String cmdParse(String command) {
        // 找到最后一个空格的位置
        int lastSpaceIndex = command.lastIndexOf(" ");
        // 如果没有空格，则整个命令就是关键词
        if (lastSpaceIndex == -1) {
            return command;
        }
        // 返回最后一个空格之前的内容
        return command.substring(0, lastSpaceIndex);
    }

    /**
     * 变量解析
     *
     * @param event 消息事件
     * @param cmd   q群中指令
     * @return 处理完的指令
     */
    public static String varParse(GroupMessageEvent event, String cmd) {
        VARS.putIfAbsent("user_id", event.getSender().getUserId());//初始化变量列表

        val cmdStart = cmd.split(" ")[0];//部分指令头
        String parseCmd = "";//定义最终命令用以返回
        boolean useCmd = false;

        for (Cmd cmd2 : CmdHandler.cmds.values()){//将含有昵称的指令替换为源命令
            if (cmd2.getCmd().contains(cmdStart)) useCmd = true;//如果源命令包含部分指令头，则可以视为使用该源命令

            for (String alies : cmd2.getAlies()){
                if (cmd.contains(alies)) {
                    useCmd = true;//如果指令包含该Cmd中的任意一个alie，则可以视为使用该源命令
                    break;//跳出循环
                }
            }
            if (useCmd) {
                parseCmd = cmd2.getCmd();
                break;//跳出循环
            }
        }

        //正则查找变量
        Pattern pattern = Pattern.compile(VAR_REGEX);
        Matcher matcher = pattern.matcher(parseCmd);

        while (matcher.find()) {//将 %变量% 替换为变量值
            for (int i = 0; i < matcher.groupCount(); i++){
                if (VARS.containsKey(matcher.group(i).replaceAll("%", ""))){
                    parseCmd = parseCmd.replace(matcher.group(i), VARS.get(matcher.group(i).replaceAll("%", "")));
                }
            }
        }

        val cmdEndSplits = parseCmd.split(" ");//拆分命令
        val cmdSplits = cmd.split(" ");

        for (int i = 0; i < cmdEndSplits.length; i++){
            if (cmdEndSplits[i].equals("%")){//如果存在变量标志
                cmdEndSplits[i] = cmdSplits[i];//则将指令中的变量对应内容传递到最终命令
            }
        }

        val endCmd = new StringBuilder();
        for (String key : cmdEndSplits) {
            endCmd.append(key).append(" ");
        }//拼接回命令

        return endCmd.toString();



    }
}
