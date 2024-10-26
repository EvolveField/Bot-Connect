package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class SendCommand {

    public static int qqLeaveExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().getSQqLeaveEnable().setBooleanValue(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().getSEnable().setBooleanValue(true);
            context.getSource().sendSuccess(() -> Component.literal("发送离开QQ群的消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送离开QQ群的消息开关已被设置为关闭"), true);
        }
        ModConfig.get().save();
        return 1;
    }


    public static int qqWelcomeExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().getSQqWelcomeEnable().setBooleanValue(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().getSEnable().setBooleanValue(true);
            context.getSource().sendSuccess(() -> Component.literal("发送新人加入QQ群的消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送新人加入QQ群的消息开关已被设置为关闭"), true);
        }
        ModConfig.get().save();
        return 1;
    }

    public static int allExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().getSEnable().setBooleanValue(true);
        if (isEnabled) {
            context.getSource().sendSuccess(() -> Component.literal("全局发送消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("全局发送消息开关已被设置为关闭"), true);
        }
        ModConfig.get().save();
        return 1;
    }

    public static int joinExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().getSJoinEnable().setBooleanValue(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().getSEnable().setBooleanValue(true);
            context.getSource().sendSuccess(() -> Component.literal("发送玩家加入游戏消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送玩家加入游戏消息开关已被设置为关闭"), true);
        }
        ModConfig.get().save();
        return 1;
    }

    public static int leaveExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().getSLeaveEnable().setBooleanValue(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().getSEnable().setBooleanValue(true);
            context.getSource().sendSuccess(() -> Component.literal("发送玩家离开游戏消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送玩家离开游戏消息开关已被设置为关闭"), true);
        }
        ModConfig.get().save();
        return 1;
    }

    public static int deathExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().getSDeathEnable().setBooleanValue(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().getSEnable().setBooleanValue(true);
            context.getSource().sendSuccess(() -> Component.literal("发送玩家死亡游戏消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送玩家死亡游戏消息开关已被设置为关闭"), true);
        }
        ModConfig.get().save();
        return 1;
    }

    public static int chatExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().getSChatEnable().setBooleanValue(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().getSEnable().setBooleanValue(true);
            context.getSource().sendSuccess(() -> Component.literal("发送玩家聊天游戏消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送玩家聊天游戏消息开关已被设置为关闭"), true);
        }
        ModConfig.get().save();
        return 1;
    }

    public static int achievementsExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.get().getStatus().getSAdvanceEnable().setBooleanValue(isEnabled);
        if (isEnabled) {
            ModConfig.get().getStatus().getSEnable().setBooleanValue(true);
            context.getSource().sendSuccess(() -> Component.literal("发送玩家成就游戏消息开关已被设置为打开"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("发送玩家成就游戏消息开关已被设置为关闭"), true);
        }
        ModConfig.get().save();
        return 1;
    }

}
