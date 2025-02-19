package cn.evole.mods.mcbot.common.event;

import cn.evole.mods.mcbot.api.bot.BotApi;
import cn.evole.mods.mcbot.api.cmd.CmdApi;
import cn.evole.mods.mcbot.api.data.ChatRecordApi;
import cn.evole.mods.mcbot.common.config.ModConfig;
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

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/3/20 8:13
 * Version: 1.0
 */
public class IBotEvent implements Listener {
    @SubscribeEvent
    public void onGroup(GroupMessageEvent event) {
        if (ModConfig.get().getCommon().getGroupIdList().getValue().contains(String.valueOf(event.getGroupId()))//判断是否是配置中的群
                && ModConfig.get().getStatus().getREnable().getValue()//总接受开关
                && !String.valueOf(event.getUserId()).equals(ModConfig.get().getBotConfig().getBotId().getValue())//过滤机器人
        ) {
            String send = CQUtils.replace(event, 2000);//暂时匹配仅符合字符串聊天内容与图片
            if (!send.startsWith(ModConfig.get().getCmd().getCmdStart().getValue())//过滤命令前缀
            ) {
                if (ModConfig.get().getStatus().getRChatEnable().getValue())/*接受聊天开关*/ onGroupMessage(event, send);
            } else if (ModConfig.get().getStatus().getRCmdEnable().getValue()//接受命令开关
            ) {
                onGroupCmd(event, send);
            }
        }
    }


    private void onGroupMessage(GroupMessageEvent event, String send) {
        if (ModConfig.get().getCmd().getQqChatPrefixOn().getValue()) {
            val split = send.split(" ");
            if (ModConfig.get().getCmd().getQqChatPrefix().getValue().equals(split[0])) //指定前缀发送
                send = split[1];
            else return;
        }

        val nick = event.getSender().getNickname();
        String groupNick = ModConfig.get().getCmd().getGroupNickOn().getValue() // 是否使用群昵称
                ? nick == null ? event.getSender().getCard() : nick // 防止api返回为空
                : event.getSender().getNickname();

        String finalMsg = ModConfig.get().getCmd().getGamePrefixOn().getValue()
                ? ModConfig.get().getCmd().getIdGamePrefixOn().getValue()
                ? String.format("§b[§l%s§r(§5%s§r)§b]§a<%s>§f %s", ModConfig.get().getCmd().getQqGamePrefix().getValue(), event.getGroupId(), groupNick, send)
                : String.format("§b[§l%s§b]§a<%s>§f %s", ModConfig.get().getCmd().getQqGamePrefix().getValue(), groupNick, send)
                : String.format("§a<%s>§f %s", groupNick, send);

        ChatRecordApi.syncAdd(String.valueOf(event.getMessageId()), String.valueOf(event.getGroupId()), String.valueOf(event.getSelfId()), finalMsg);

        BotApi.sendAllPlayerMsg(finalMsg);
    }


    private void onGroupCmd(GroupMessageEvent event, String rawMsg) {
        CmdApi.invokeGroupCommand(event, rawMsg);
    }

    @SubscribeEvent
    public void onGroupMemberJoin(GroupIncreaseNoticeEvent event) {
        if (ModConfig.get().getCommon().getGroupIdList().getValue().contains(String.valueOf(event.getGroupId()))
                && ModConfig.get().getStatus().getSEnable().getValue()
                && ModConfig.get().getStatus().getSQqWelcomeEnable().getValue()) {
            val msg = MsgUtils.builder().at(event.getUserId()).text(ModConfig.get().getCmd().getWelcomeNotice().getValue()).build();
            BotApi.sendGroupMsg(event.getGroupId(), msg);
        }
    }

    @SubscribeEvent
    public void onGroupMemberQuit(GroupDecreaseNoticeEvent event) {
        if (ModConfig.get().getCommon().getGroupIdList().getValue().contains(String.valueOf(event.getGroupId()))
                && ModConfig.get().getStatus().getSEnable().getValue()
                && ModConfig.get().getStatus().getSQqLeaveEnable().getValue()) {
            val msg = MsgUtils.builder().at(event.getUserId()).text(ModConfig.get().getCmd().getLeaveNotice().getValue()).build();
            BotApi.sendGroupMsg(event.getGroupId(), msg);
        }
    }

    @SubscribeEvent
    public void onLifeCycle(LifecycleMetaEvent event) {
        if (!event.getSubType().equals("connect")) return;
        if (ModConfig.get().getStatus().getConnectInfoEnable().getValue()
                &&!ModConfig.get().getCommon().getGroupIdList().getValue().isEmpty()
        ) {
            val msg = "▌ 群服互联已连接 ┈━═☆";
            BotApi.sendAllGroupMsg(msg);
        }
    }

    @SubscribeEvent
    public void onHeartbeat(HeartbeatMetaEvent event) {
        // McBot.keepAlive.onHeartbeat(event);
    }
}
