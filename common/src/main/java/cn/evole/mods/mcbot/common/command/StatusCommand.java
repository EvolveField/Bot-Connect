package cn.evole.mods.mcbot.common.command;


import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.val;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class StatusCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean clientEnabled = ModConfig.get().getCommon().getEnable().getBooleanValue();

        boolean receiveEnabled = ModConfig.get().getStatus().getREnable().getBooleanValue();
        boolean rChatEnabled = ModConfig.get().getStatus().getRChatEnable().getBooleanValue();
        boolean rCmdEnabled = ModConfig.get().getStatus().getRCmdEnable().getBooleanValue();

        boolean sendEnabled = ModConfig.get().getStatus().getSEnable().getBooleanValue();
        boolean sJoinEnabled = ModConfig.get().getStatus().getSJoinEnable().getBooleanValue();
        boolean sLeaveEnabled = ModConfig.get().getStatus().getSLeaveEnable().getBooleanValue();
        boolean sDeathEnabled = ModConfig.get().getStatus().getSDeathEnable().getBooleanValue();
        boolean sAchievementsEnabled = ModConfig.get().getStatus().getSAdvanceEnable().getBooleanValue();
        boolean sQqWelcomeEnabled = ModConfig.get().getStatus().getSQqWelcomeEnable().getBooleanValue();
        boolean sQqLeaveEnabled = ModConfig.get().getStatus().getSQqLeaveEnable().getBooleanValue();

        val groupId = ModConfig.get().getCommon().getGroupIdList().getStrings().toString();
        boolean debuggable = ModConfig.get().getCommon().getDebug().getBooleanValue();
        boolean connected = Constants.onebot.getWs().isOpen();
        boolean white = Constants.SERVER.getPlayerList().isUsingWhitelist();
        String host = ModConfig.get().getBotConfig().getUrl().getStringValue();
        String QQid = ModConfig.get().getBotConfig().getBotId().getStringValue();
        String toSend =
                "\n姬妻人服务状态:\n"
                        + "姬妻人QQId:" + QQid + " \n"
                        + "框架服务器:" + host + " \n"
                        + "WebSocket连接状态:" + connected + "\n"
                        + "互通的群号:" + groupId + "\n"
                        + "全局服务状态:" + clientEnabled + "\n"
                        + "开发者模式状态:" + debuggable + "\n"
                        + "白名单是否开启:" + white + "\n"
                        + "*************************************\n"
                        + "全局接收消息状态:" + receiveEnabled + "\n"
                        + "接收QQ群聊天消息状态:" + rChatEnabled + "\n"
                        + "接收QQ群命令消息状态:" + rCmdEnabled + "\n"
                        + "*************************************\n"
                        + "全局发送消息状态:" + sendEnabled + "\n"
                        + "发送玩家加入消息状态:" + sJoinEnabled + "\n"
                        + "发送玩家离开消息状态:" + sLeaveEnabled + "\n"
                        + "发送玩家死亡消息状态:" + sDeathEnabled + "\n"
                        + "发送玩家成就消息状态:" + sAchievementsEnabled + "\n"
                        + "发送群成员进群消息状态:" + sQqWelcomeEnabled + "\n"
                        + "发送群成员退群消息状态:" + sQqLeaveEnabled + "\n";
        context.getSource().sendSuccess(() -> Component.literal(toSend), true);
        ModConfig.get().save();
        return 1;
    }
}
