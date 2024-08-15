package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class SendCommand {

    public static int qqLeaveExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().setSQqLeaveEnable(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().setSEnable(true);
            context.getSource().sendSuccess(() -> Component.literal("发送离开QQ群的消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送离开QQ群的消息开关已被设置为关闭"), true);
        }
        ModConfig.save();
        return 1;
    }


    public static int qqWelcomeExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().setSQqWelcomeEnable(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().setSEnable(true);
            context.getSource().sendSuccess(() -> Component.literal("发送新人加入QQ群的消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送新人加入QQ群的消息开关已被设置为关闭"), true);
        }
        ModConfig.save();
        return 1;
    }

    public static int allExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().setSEnable(isEnabled);
        if (isEnabled) {
            context.getSource().sendSuccess(() -> Component.literal("全局发送消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("全局发送消息开关已被设置为关闭"), true);
        }
        ModConfig.save();
        return 1;
    }

    public static int joinExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().setSJoinEnable(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().setSEnable(true);
            context.getSource().sendSuccess(() -> Component.literal("发送玩家加入游戏消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送玩家加入游戏消息开关已被设置为关闭"), true);
        }
        ModConfig.save();
        return 1;
    }

    public static int leaveExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().setSLeaveEnable(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().setSEnable(true);
            context.getSource().sendSuccess(() -> Component.literal("发送玩家离开游戏消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送玩家离开游戏消息开关已被设置为关闭"), true);
        }
        ModConfig.save();
        return 1;
    }

    public static int deathExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().setSDeathEnable(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().setSEnable(true);
            context.getSource().sendSuccess(() -> Component.literal("发送玩家死亡游戏消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送玩家死亡游戏消息开关已被设置为关闭"), true);
        }
        ModConfig.save();
        return 1;
    }

    public static int chatExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().setSChatEnable(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().setSEnable(true);
            context.getSource().sendSuccess(() -> Component.literal("发送玩家聊天游戏消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送玩家聊天游戏消息开关已被设置为关闭"), true);
        }
        ModConfig.save();
        return 1;
    }

    public static int achievementsExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().setSAdvanceEnable(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().setSEnable(true);
            context.getSource().sendSuccess(() -> Component.literal("发送玩家成就游戏消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送玩家成就游戏消息开关已被设置为关闭"), true);
        }
        ModConfig.save();
        return 1;
    }

}
