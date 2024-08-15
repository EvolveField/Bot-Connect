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
        boolean clientEnabled = ModConfig.get().getCommon().isEnable();

        boolean receiveEnabled = ModConfig.get().getStatus().isREnable();
        boolean rChatEnabled = ModConfig.get().getStatus().isRChatEnable();
        boolean rCmdEnabled = ModConfig.get().getStatus().isRCmdEnable();

        boolean sendEnabled = ModConfig.get().getStatus().isSEnable();
        boolean sJoinEnabled = ModConfig.get().getStatus().isSJoinEnable();
        boolean sLeaveEnabled = ModConfig.get().getStatus().isSLeaveEnable();
        boolean sDeathEnabled = ModConfig.get().getStatus().isSDeathEnable();
        boolean sAchievementsEnabled = ModConfig.get().getStatus().isSAdvanceEnable();
        boolean sQqWelcomeEnabled = ModConfig.get().getStatus().isSQqWelcomeEnable();
        boolean sQqLeaveEnabled = ModConfig.get().getStatus().isSQqLeaveEnable();

        val groupId = ModConfig.get().getCommon().getGroupIdList().toString();
        boolean debuggable = ModConfig.get().getCommon().isDebug();
        boolean connected = Constants.onebot.getWs().isOpen();
        boolean white = Constants.SERVER.getPlayerList().isUsingWhitelist();
        String host = ModConfig.get().getBotConfig().getUrl();
        long QQid = ModConfig.get().getBotConfig().getBotId();
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
        ModConfig.save();
        return 1;
    }
}
