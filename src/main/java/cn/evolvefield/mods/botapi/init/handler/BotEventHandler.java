package cn.evolvefield.mods.botapi.init.handler;

import cn.evolvefield.mods.botapi.BotApi;
import cn.evolvefield.mods.botapi.api.message.MiraiMessage;
import cn.evolvefield.mods.botapi.api.message.SendMessage;
import cn.evolvefield.mods.botapi.core.bot.BotData;
import cn.evolvefield.mods.botapi.core.bot.Invoke;
import cn.evolvefield.mods.botapi.init.callbacks.BotEvents;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/3/20 8:13
 * Version: 1.0
 */
public class BotEventHandler {
    public static void init() {
        BotEvents.GROUP_MSG_EVENT.register((event) -> {

            if (event.getGroupId() == BotApi.config.getCommon().getGroupId() && BotApi.config.getStatus().isRECEIVE_ENABLED()) {

                if (BotData.getBotFrame().equalsIgnoreCase("cqhttp")) {
                    if (BotApi.config.getCommon().isDebuggable()) {
                        BotApi.LOGGER.info("收到群" + event.getGroupId() + "发送消息" + event.getMessage());
                    }
                    if (event.getMessage().startsWith(BotApi.config.getCmd().getCommandStart()) && BotApi.config.getStatus().isR_COMMAND_ENABLED()) {

                        Invoke.invokeCommand(event);

                    } else if (!event.getMessage().startsWith("[CQ:") && BotApi.config.getStatus().isR_CHAT_ENABLE()) {
                        String toSend = String.format("§b[§lQQ§r§b]§a<%s>§f %s", event.getNickName(), event.getMessage());
                        TickEventHandler.getToSendQueue().add(toSend);
                    }
                } else if (BotData.getBotFrame().equalsIgnoreCase("mirai")) {
                    if (BotApi.config.getCommon().isDebuggable()) {
                        for (MiraiMessage msg : event.getMiraiMessage()) {
                            msg.deBug();
                        }
                        System.out.println(event.getMiraiMessage().get(1).getText());
                    }
                    if (event.getMiraiMessage().get(1).getText().startsWith(BotApi.config.getCmd().getCommandStart()) && BotApi.config.getStatus().isR_COMMAND_ENABLED()) {

                        Invoke.invokeCommand(event);

                    } else if (!event.getMiraiMessage().get(1).getText().startsWith(BotApi.config.getCmd().getCommandStart()) && BotApi.config.getStatus().isR_CHAT_ENABLE()) {
                        String toSend = String.format("§b[§lQQ§r§b]§a<%s>§f %s", event.getNickName(), event.getMiraiMessage().get(1).getText());
                        TickEventHandler.getToSendQueue().add(toSend);
                    }
                } else {
                    System.out.println("错误");
                }

            }
        });

        BotEvents.PRIVATE_MSG_EVENT.register((event) -> {


        });

        BotEvents.NOTICE_MSG_EVENT.register((event) -> {
            if (BotApi.config.getStatus().isS_WELCOME_ENABLE()
                    && BotApi.config.getStatus().isSEND_ENABLED()
                    && event.getGroup_id() == BotApi.config.getCommon().getGroupId()) {
                if (event.getNoticeType().equals("group_increase")) {
                    SendMessage.Group(BotApi.config.getCommon().getGroupId(), BotApi.config.getCmd().getWelcomeNotice());
                } else if (event.getNoticeType().equals("group_decrease")) {
                    SendMessage.Group(BotApi.config.getCommon().getGroupId(), BotApi.config.getCmd().getLeaveNotice());
                }
            }

        });


        BotEvents.REQUEST_MSG_EVENT.register((event) -> {


        });
    }

}
