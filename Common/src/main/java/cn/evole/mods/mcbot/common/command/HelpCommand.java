package cn.evole.mods.mcbot.common.command;


import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.val;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
//#endif

public class HelpCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        String toSend =
                "\n群服互联使用说明:\n"
                        + "如果你是第一次使用请按照以下步骤设置\n"
                        + "1.请先开启机器人框架，go-cqhttp或者mirai\n"
                        + "2.请使用/mcbot addGroup <GroupId> 添加互通的群\n"
                        + "3.请使用/mcbot setBot <BotId> 设置机器人的qq号\n"
                        + "4.如果打开了鉴权验证，请输入/mcbot setAuthKey <AuthKey> 设置\n"
                        + "5.准备工作完成，请使用/mcbot connect <host:port> 与框架对接\n"
                        + "*************************************\n"
                        + "在框架默认配置下，请使用/mcbot connect\n"
                        + "*************************************\n"
                        + "全部命令：\n"
                        + "/mcbot connect <host:port>\n"
                        + "/mcbot addGroup <GroupId>       添加群\n"
                        + "/mcbot delGroup <GroupId>    删除群\n"
                        + "/mcbot addChannel <ChannelId>       添加群\n"
                        + "/mcbot delChannel <ChannelId>    删除群\n"
                        + "/mcbot setBot <BotId>           设置机器人id\n"
                        + "/mcbot setAuthKey <AuthKey> 鉴权开启时使用\n"
                        + "/mcbot receive <all|chat|cmd> <true|false>\n"
                        + "/mcbot send <all|join|leave|death|achievements|qqWelcome|qqLeave> <true|false>\n"
                        + "/mcbot status   机器人状态\n"
                        + "/mcbot help     帮助\n"
                        + "/mcbot reload   重载配置\n"
                        + "/mcbot customs  列出所有自定义命令\n"
                        + "*************************************\n"
                        + "感谢您的支持，如有问题请联系我\n"
                        + "QQ群：720975019找群主\n"
                        + "Github：\n";

        val url = "https://github.com/Nova-Committee/Bot-Connect/issues/new";
        val end = "提交问题";
        val urlC = Component.literal(url).setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Nova-Committee/Bot-Connect/issues/new")));
        val endC = Component.literal(end);


        context.getSource().sendSuccess(() -> Component.literal(toSend).append(urlC).append(endC), true);
        return 1;
    }
}
