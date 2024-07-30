package cn.evole.mods.mcbot.command;


import cn.evole.mods.mcbot.McBot;
import cn.evole.mods.mcbot.config.ConfigManager;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.val;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#if MC <11900
import net.minecraft.network.chat.TextComponent;
//#endif
public class StatusCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean clientEnabled =  ConfigManager.instance().getCommon().isEnable();

        boolean receiveEnabled =  ConfigManager.instance().getStatus().isREnable();
        boolean rChatEnabled =  ConfigManager.instance().getStatus().isRChatEnable();
        boolean rCmdEnabled =  ConfigManager.instance().getStatus().isRCmdEnable();

        boolean sendEnabled =  ConfigManager.instance().getStatus().isSEnable();
        boolean sJoinEnabled =  ConfigManager.instance().getStatus().isSJoinEnable();
        boolean sLeaveEnabled =  ConfigManager.instance().getStatus().isSLeaveEnable();
        boolean sDeathEnabled =  ConfigManager.instance().getStatus().isSDeathEnable();
        boolean sAchievementsEnabled =  ConfigManager.instance().getStatus().isSAdvanceEnable();
        boolean sQqWelcomeEnabled =  ConfigManager.instance().getStatus().isSQqWelcomeEnable();
        boolean sQqLeaveEnabled =  ConfigManager.instance().getStatus().isSQqLeaveEnable();

        val groupId =  ConfigManager.instance().getCommon().getGroupIdList().toString();
        boolean debuggable =  ConfigManager.instance().getCommon().isDebug();
        boolean connected = McBot.onebot.getWs().isOpen();
        boolean white = McBot.SERVER.getPlayerList().isUsingWhitelist();
        String host =  ConfigManager.instance().getBotConfig().getUrl();
        long QQid =  ConfigManager.instance().getBotConfig().getBotId();
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
        //#if MC >= 12000
        //$$ context.getSource().sendSuccess(()->Component.literal(toSend), true);
        //#elseif MC < 11900
        context.getSource().sendSuccess(new TextComponent(toSend), true);
        //#else
        //$$ context.getSource().sendSuccess(Component.literal(toSend), true);
        //#endif
        return 1;
    }
}
