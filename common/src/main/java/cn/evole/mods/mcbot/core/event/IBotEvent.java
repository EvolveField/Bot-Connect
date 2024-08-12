package cn.evole.mods.mcbot.core.event;

import cn.evole.mods.mcbot.Const;
import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.McBot;
import cn.evole.mods.mcbot.api.bot.BotApi;
import cn.evole.mods.mcbot.cmds.CmdApi;
import cn.evole.mods.mcbot.core.data.ChatRecordApi;
import cn.evole.mods.mcbot.util.onebot.CQUtils;
import cn.evole.onebot.client.annotations.SubscribeEvent;
import cn.evole.onebot.client.interfaces.Listener;
import cn.evole.onebot.sdk.event.message.GroupMessageEvent;
import cn.evole.onebot.sdk.event.meta.HeartbeatMetaEvent;
import cn.evole.onebot.sdk.event.meta.LifecycleMetaEvent;
import cn.evole.onebot.sdk.event.notice.group.GroupDecreaseNoticeEvent;
import cn.evole.onebot.sdk.event.notice.group.GroupIncreaseNoticeEvent;
import cn.evole.onebot.sdk.util.MsgUtils;
import lombok.val;
//#if MC <11900

//#endif


/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/3/20 8:13
 * Version: 1.0
 */
public class IBotEvent implements Listener {
    @SubscribeEvent
    public void onGroup(GroupMessageEvent event){
            if (Constants.configManager.config().getCommon().getGroupIdList().contains(event.getGroupId())//判断是否是配置中的群
                    && Constants.configManager.config().getStatus().isREnable()//总接受开关
                    && event.getUserId() != Constants.configManager.config().getBotConfig().getBotId()//过滤机器人
            ){
                String send = CQUtils.replace(event, 3000);//暂时匹配仅符合字符串聊天内容与图片
                if (!send.startsWith(Constants.configManager.config().getCmd().getCmdStart())//过滤命令前缀
                ) {
                    if (Constants.configManager.config().getStatus().isRChatEnable())/*接受聊天开关*/ onGroupMessage(event, send);
                }
                else if (Constants.configManager.config().getStatus().isRCmdEnable()//接受命令开关
                ){
                    onGroupCmd(event, send);
                }
            }
    }


    private void onGroupMessage(GroupMessageEvent event, String send) {
            if (Constants.configManager.config().getCmd().isQqChatPrefixOn()) {
                val split = send.split(" ");
                if (Constants.configManager.config().getCmd().getQqChatPrefix().equals(split[0])) //指定前缀发送
                    send = split[1];
                else return;
            }
            val nick = event.getSender().getNickname();
            String groupNick = Constants.configManager.config().getCmd().isGroupNickOn() // 是否使用群昵称
                    ? nick == null ? event.getSender().getCard() : nick // 防止api返回为空
                    : event.getSender().getNickname();

            String finalMsg = Constants.configManager.config().getCmd().isGamePrefixOn()
                    ? Constants.configManager.config().getCmd().isIdGamePrefixOn()
                    ? String.format("§b[§l%s§r(§5%s§b)]§a<%s>§f %s", Constants.configManager.config().getCmd().getQqGamePrefix(), event.getGroupId(), groupNick, send)
                    : String.format("§b[§l%s§b]§a<%s>§f %s", Constants.configManager.config().getCmd().getQqGamePrefix(), groupNick, send)
                    : String.format("§a<%s>§f %s", groupNick, send);

            ChatRecordApi.add(String.valueOf(event.getMessageId()), String.valueOf(event.getGroupId()), String.valueOf(event.getSelfId()), finalMsg);

            BotApi.sendAllPlayerMsg(finalMsg);
    }


    private void onGroupCmd(GroupMessageEvent event, String rawMsg) {
        CmdApi.invokeCommandGroup(rawMsg, event);
    }

    @SubscribeEvent
    public void onGroupMemberJoin(GroupIncreaseNoticeEvent event) {
        if (Constants.configManager.config().getCommon().getGroupIdList().contains(event.getGroupId())
                && Constants.configManager.config().getStatus().isSEnable()
                && Constants.configManager.config().getStatus().isSQqWelcomeEnable()) {
            val msg = MsgUtils.builder().at(event.getUserId()).text(Constants.configManager.config().getCmd().getWelcomeNotice()).build();
            BotApi.sendGroupMsg(event.getGroupId(), msg);
        }
    }

    @SubscribeEvent
    public void onGroupMemberQuit(GroupDecreaseNoticeEvent event) {
        if (Constants.configManager.config().getCommon().getGroupIdList().contains(event.getGroupId())
                && Constants.configManager.config().getStatus().isSEnable()
                && Constants.configManager.config().getStatus().isSQqLeaveEnable()) {
            val msg = MsgUtils.builder().at(event.getUserId()).text(Constants.configManager.config().getCmd().getLeaveNotice()).build();
            BotApi.sendGroupMsg(event.getGroupId(), msg);
        }
    }

    @SubscribeEvent
    public void onLifeCycle(LifecycleMetaEvent event) {
        if (!event.getSubType().equals("connect")) return;
        if (!Constants.configManager.config().getCommon().getGroupIdList().isEmpty()
        ) {
            val msg = MsgUtils.builder().text("▌ 群服互联已连接 ┈━═☆").build();
            BotApi.sendAllGroupMsg(msg);
        }
    }

    @SubscribeEvent
    public void onHeartbeat(HeartbeatMetaEvent event) {
        McBot.keepAlive.onHeartbeat(event);
    }
}
