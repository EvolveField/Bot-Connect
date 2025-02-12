package cn.evole.mods.mcbot.api.bot;

import cn.evole.mods.mcbot.common.config.ModConfig;
import cn.evole.mods.mcbot.common.event.ITickEvent;
import cn.evole.mods.mcbot.util.MsgThreadUtils;
import cn.evole.onebot.sdk.action.misc.ActionPath;
import com.google.gson.JsonObject;
import lombok.val;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.concurrent.Callable;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/12 01:34
 * @Description:
 */
public class BotApi {
    public static void sendGroupMsg(long group_id, String message) {
        MsgThreadUtils.INSTANCE.submit(group_id, message, false);
    }

    public static void sendGroupMsg(long group_id, Callable<String> message) {
        MsgThreadUtils.INSTANCE.submit(group_id, message, false);
    }

    public static void sendAllGroupMsg(String message) {
        for (String id : ModConfig.get().getCommon().getGroupIdList().getValue()) {
            sendGroupMsg(Long.parseLong(id), message);
        }
    }

    public static void sendAllGroupMsg(Callable<String> message) {
        for (String id : ModConfig.get().getCommon().getGroupIdList().getValue()) {
            sendGroupMsg(Long.parseLong(id), message);
        }
    }

    /**
     * 玩家在游戏里发送消息
     *
     * @param message 消息
     * @param player  玩家
     */
    public static void sendAllGroupMsg(Callable<String> message, ServerPlayer player) {
        for (String id : ModConfig.get().getCommon().getGroupIdList().getValue()) {
            MsgThreadUtils.INSTANCE.submit(Long.parseLong(id), message, false, player);
        }
    }

    /**
     * 向游戏中的所有人发送消息
     */
    public static void sendAllPlayerMsg(String message) {
        val toSend = Component.literal(message);
        ITickEvent.sendQueue().add(toSend);
    }

    /**
     * 自定义请求 (不应清理)
     *
     * @param action 请求类型
     * @param params 参数
     */
    public static void customRequest(ActionPath action, JsonObject params) {
        MsgThreadUtils.INSTANCE.submit(action, params);
    }

}
