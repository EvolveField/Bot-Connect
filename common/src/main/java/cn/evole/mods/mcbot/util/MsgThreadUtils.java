package cn.evole.mods.mcbot.util;

import cn.evole.mods.mcbot.api.event.mod.McBotEvents;
import cn.evole.onebot.sdk.action.misc.ActionPath;
import com.google.gson.JsonObject;
import lombok.val;
import net.minecraft.server.level.ServerPlayer;

import java.util.concurrent.Callable;

import static cn.evole.mods.mcbot.Constants.*;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/12 01:28
 * @Description:
 */
public class MsgThreadUtils {
    public static final MsgThreadUtils INSTANCE = new MsgThreadUtils();

    public void submit(long groupId, String msg, boolean autoEscape) {
        LOGGER.debug("转发游戏消息: {}", msg);
        msgExecutor.submit(() -> onebot.getBot().sendGroupMsg(groupId, msg, autoEscape));
    }

    public void submit(long groupId, Callable<String> msg, boolean autoEscape) {
        msgExecutor.submit(() -> {
            try {
                val message = msg.call();
                LOGGER.debug("转发游戏消息: {}", message);
                onebot.getBot().sendGroupMsg(groupId, message, autoEscape);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void submit(long groupId, Callable<String> msg, boolean autoEscape, ServerPlayer player) {
        msgExecutor.submit(() -> {
            try {
                val message = msg.call();
                LOGGER.debug("转发游戏消息: {}", message);
                McBotEvents.ON_CHAT.invoker().onChat(player,
                        onebot.getBot().sendGroupMsg(groupId, message, autoEscape).getData().getMessageId(),
                        message
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void submit(ActionPath action, JsonObject params) {
        LOGGER.info("执行自定义操作：{}", action);
        msgExecutor.submit(() -> onebot.getBot().customRequest(action, params));
    }

    public void register(Runnable callable) {
        msgExecutor.submit(callable);
    }

}
