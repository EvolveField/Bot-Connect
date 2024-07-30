package cn.evole.mods.mcbot.command;

import cn.evole.mods.mcbot.config.ConfigManager;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#if MC <11900
import net.minecraft.network.chat.TextComponent;
//#endif


public class SendCommand {


    public static int qqLeaveExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ConfigManager.instance().getStatus().setSQqLeaveEnable(isEnabled);
        if (isEnabled) {
            ConfigManager.instance().getStatus().setSEnable(true);
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送离开QQ群的消息开关已被设置为打开"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送新人加入QQ群的消息开关已被设置为打开"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送新人加入QQ群的消息开关已被设置为打开"), true);
            //#endif
        } else {
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送离开QQ群的消息开关已被设置为关闭"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送新人加入QQ群的消息开关已被设置为关闭"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送新人加入QQ群的消息开关已被设置为关闭"), true);
            //#endif
        }
        return 1;
    }


    public static int qqWelcomeExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ConfigManager.instance().getStatus().setSQqWelcomeEnable(isEnabled);
        if (isEnabled) {
            ConfigManager.instance().getStatus().setSEnable(true);
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送新人加入QQ群的消息开关已被设置为打开"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送新人加入QQ群的消息开关已被设置为打开"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送新人加入QQ群的消息开关已被设置为打开"), true);
            //#endif
        } else {
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送新人加入QQ群的消息开关已被设置为关闭"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送新人加入QQ群的消息开关已被设置为关闭"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送新人加入QQ群的消息开关已被设置为关闭"), true);
            //#endif
        }
        return 1;
    }

    public static int allExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ConfigManager.instance().getStatus().setSEnable(isEnabled);
        if (isEnabled) {
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("全局发送消息开关已被设置为打开"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("全局发送消息开关已被设置为打开"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("全局发送消息开关已被设置为打开"), true);
            //#endif
        } else {
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("全局发送消息开关已被设置为关闭"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("全局发送消息开关已被设置为关闭"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("全局发送消息开关已被设置为关闭"), true);
            //#endif
        }
        return 1;
    }

    public static int joinExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ConfigManager.instance().getStatus().setSJoinEnable(isEnabled);
        if (isEnabled) {
            ConfigManager.instance().getStatus().setSEnable(true);
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送玩家加入游戏消息开关已被设置为打开"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送玩家加入游戏消息开关已被设置为打开"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送玩家加入游戏消息开关已被设置为打开"), true);
            //#endif
        } else {
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送玩家加入游戏消息开关已被设置为关闭"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送玩家加入游戏消息开关已被设置为关闭"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送玩家加入游戏消息开关已被设置为关闭"), true);
            //#endif
        }
        return 1;
    }

    public static int leaveExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ConfigManager.instance().getStatus().setSLeaveEnable(isEnabled);
        if (isEnabled) {
            ConfigManager.instance().getStatus().setSEnable(true);
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送玩家离开游戏消息开关已被设置为打开"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送玩家离开游戏消息开关已被设置为打开"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送玩家离开游戏消息开关已被设置为打开"), true);
            //#endif
        } else {
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送玩家离开游戏消息开关已被设置为关闭"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送玩家离开游戏消息开关已被设置为关闭"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送玩家离开游戏消息开关已被设置为关闭"), true);
            //#endif
        }
        return 1;
    }

    public static int deathExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ConfigManager.instance().getStatus().setSDeathEnable(isEnabled);
        if (isEnabled) {
            ConfigManager.instance().getStatus().setSEnable(true);
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送玩家死亡游戏消息开关已被设置为打开"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送玩家死亡游戏消息开关已被设置为打开"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送玩家死亡游戏消息开关已被设置为打开"), true);
            //#endif
        } else {
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送玩家死亡游戏消息开关已被设置为关闭"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送玩家死亡游戏消息开关已被设置为关闭"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送玩家死亡游戏消息开关已被设置为关闭"), true);
            //#endif
        }
        return 1;
    }

    public static int chatExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ConfigManager.instance().getStatus().setSChatEnable(isEnabled);
        if (isEnabled) {
            ConfigManager.instance().getStatus().setSEnable(true);
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送玩家聊天游戏消息开关已被设置为打开"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送玩家聊天游戏消息开关已被设置为打开"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送玩家聊天游戏消息开关已被设置为打开"), true);
            //#endif
        } else {
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送玩家聊天游戏消息开关已被设置为关闭"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送玩家聊天游戏消息开关已被设置为关闭"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送玩家聊天游戏消息开关已被设置为关闭"), true);
            //#endif
        }
        return 1;
    }

    public static int achievementsExecute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ConfigManager.instance().getStatus().setSAdvanceEnable(isEnabled);
        if (isEnabled) {
            ConfigManager.instance().getStatus().setSEnable(true);
            //#if MC >= 12000
            //$$  context.getSource().sendSuccess(()->Component.literal("发送玩家成就游戏消息开关已被设置为打开"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送玩家成就游戏消息开关已被设置为打开"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送玩家成就游戏消息开关已被设置为打开"), true);
            //#endif
        } else {
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("发送玩家成就游戏消息开关已被设置为关闭"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("发送玩家成就游戏消息开关已被设置为关闭"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("发送玩家成就游戏消息开关已被设置为关闭"), true);
            //#endif
        }
        return 1;
    }

}
