package cn.evole.mods.mcbot.core.event;

import cn.evole.mods.mcbot.Const;
import cn.evole.mods.mcbot.McBot;
import cn.evole.mods.mcbot.cmds.CmdApi;
import cn.evole.mods.mcbot.config.ConfigManager;
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
            if (ConfigManager.instance().getCommon().getGroupIdList().contains(event.getGroupId())//判断是否是配置中的群
                    && ConfigManager.instance().getStatus().isREnable()//总接受开关
                    && event.getUserId() != ConfigManager.instance().getBotConfig().getBotId()//过滤机器人
            ){
                String send = CQUtils.replace(event, 3000);//暂时匹配仅符合字符串聊天内容与图片
                if (!send.startsWith(ConfigManager.instance().getCmd().getCmdStart())//过滤命令前缀
                ) {
                    if (ConfigManager.instance().getStatus().isRChatEnable())/*接受聊天开关*/ onGroupMessage(event, send);
                }
                else if (ConfigManager.instance().getStatus().isRCmdEnable()//接受命令开关
                ){
                    onGroupCmd(event, send);
                }
            }
    }


    private void onGroupMessage(GroupMessageEvent event, String send) {
            if (ConfigManager.instance().getCmd().isQqChatPrefixOn()) {
                val split = send.split(" ");
                if (ConfigManager.instance().getCmd().getQqChatPrefix().equals(split[0])) //指定前缀发送
                    send = split[1];
                else return;
            }
            val nick = event.getSender().getNickname();
            String groupNick = ConfigManager.instance().getCmd().isGroupNickOn() // 是否使用群昵称
                    ? nick == null ? event.getSender().getCard() : nick // 防止api返回为空
                    : event.getSender().getNickname();

            String finalMsg = ConfigManager.instance().getCmd().isGamePrefixOn()
                    ? ConfigManager.instance().getCmd().isIdGamePrefixOn()
                    ? String.format("§b[§l%s§r(§5%s§b)]§a<%s>§f %s", ConfigManager.instance().getCmd().getQqGamePrefix(), event.getGroupId(), groupNick, send)
                    : String.format("§b[§l%s§b]§a<%s>§f %s", ConfigManager.instance().getCmd().getQqGamePrefix(), groupNick, send)
                    : String.format("§a<%s>§f %s", groupNick, send);

            ChatRecordApi.add(String.valueOf(event.getMessageId()), String.valueOf(event.getGroupId()), String.valueOf(event.getSelfId()), finalMsg);


            //todo 撤回机制？
//                    val recallCmd = "/mcbot recall" + event.getMessageId();
//                    val end = " [撤回]";
//                    //#if MC >= 11900
//                    //$$ val recall = Component.literal(end).withStyle(ChatFormatting.BLUE).setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, recallCmd)));
//                    //$$ val toSend = Component.literal(finalMsg).append(recall);
//                    //#else
//                    val recall = new TextComponent(end).withStyle(ChatFormatting.BLUE).setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, recallCmd)));
//                    val toSend = new TextComponent(finalMsg).append(recall);
//                    //#endif

            Const.sendAllPlayerMsg(finalMsg);
    }


    private void onGroupCmd(GroupMessageEvent event, String rawMsg) {
        CmdApi.invokeCommandGroup(rawMsg, event);
    }

    @SubscribeEvent
    public void onGroupMemberJoin(GroupIncreaseNoticeEvent event) {
        if (ConfigManager.instance().getCommon().getGroupIdList().contains(event.getGroupId())
                && ConfigManager.instance().getStatus().isSEnable()
                && ConfigManager.instance().getStatus().isSQqWelcomeEnable()) {
            val msg = MsgUtils.builder().at(event.getUserId()).text(ConfigManager.instance().getCmd().getWelcomeNotice()).build();
            Const.sendGroupMsg(event.getGroupId(), msg);
        }
    }

    @SubscribeEvent
    public void onGroupMemberQuit(GroupDecreaseNoticeEvent event) {
        if (ConfigManager.instance().getCommon().getGroupIdList().contains(event.getGroupId())
                && ConfigManager.instance().getStatus().isSEnable()
                && ConfigManager.instance().getStatus().isSQqLeaveEnable()) {
            val msg = MsgUtils.builder().at(event.getUserId()).text(ConfigManager.instance().getCmd().getLeaveNotice()).build();
            Const.sendGroupMsg(event.getGroupId(), msg);
        }
    }

    @SubscribeEvent
    public void onLifeCycle(LifecycleMetaEvent event) {
        if (!event.getSubType().equals("connect")) return;
        if (!ConfigManager.instance().getCommon().getGroupIdList().isEmpty()
        ) {
            val msg = MsgUtils.builder().text("▌ 群服互联已连接 ┈━═☆").build();
            Const.sendAllGroupMsg(msg);
        }
    }

    @SubscribeEvent
    public void onHeartbeat(HeartbeatMetaEvent event) {
        McBot.keepAlive.onHeartbeat(event);
    }
}
