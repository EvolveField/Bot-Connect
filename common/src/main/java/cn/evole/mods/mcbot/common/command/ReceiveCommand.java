package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.Constants;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#endif

public class ReceiveCommand {

    public static int allExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        Constants.configManager.config().getStatus().setREnable(isEnabled);
        if (isEnabled) {
            context.getSource().sendSuccess(() -> Component.literal("全局接收群消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("全局接收群消息开关已被设置为关闭"), true);
        }
        return 1;
    }

    public static int chatExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        Constants.configManager.config().getStatus().setRChatEnable(isEnabled);
        if (isEnabled) {
            Constants.configManager.config().getStatus().setREnable(true);
            context.getSource().sendSuccess(() -> Component.literal("接收群内聊天消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("接收群内聊天消息开关已被设置为关闭"), true);
        }
        return 1;

    }

    public static int cmdExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        Constants.configManager.config().getStatus().setRCmdEnable(isEnabled);
        if (isEnabled) {
            Constants.configManager.config().getStatus().setREnable(true);
            context.getSource().sendSuccess(() -> Component.literal("接收群内命令消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("接收群内命令消息开关已被设置为关闭"), true);
        }
        return 1;
    }
}
