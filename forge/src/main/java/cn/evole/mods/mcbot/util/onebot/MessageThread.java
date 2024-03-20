package cn.evole.mods.mcbot.util.onebot;

import cn.evole.mods.mcbot.Const;
import cn.evole.mods.mcbot.IMcBot;
import cn.evole.mods.mcbot.McBot;
import cn.evole.mods.mcbot.api.BotChatEvent;
import cn.evole.onebot.sdk.action.ActionPath;
import com.google.gson.JsonObject;
import lombok.val;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Project: McBot-fabric
 * @Author: xia-mc
 * @CreateTime: 2024/2/12 16:11
 * @Description: 笑点解析：
 */
public class MessageThread {
    private final ExecutorService executor;
    public MessageThread() {
        executor = Executors.newCachedThreadPool();
    }

    public void submit(long groupId, String msg, boolean autoEscape) {
        Const.LOGGER.debug("转发游戏消息: {}", msg);
        executor.submit(() -> IMcBot.onebot.getBot().sendGroupMsg(groupId, msg, autoEscape));
    }

    public void submit(long groupId, Callable<String> msg, boolean autoEscape) {
        executor.submit(() -> {
            try {
                val message = msg.call();
                Const.LOGGER.debug("转发游戏消息: {}", message);
                IMcBot.onebot.getBot().sendGroupMsg(groupId, message, autoEscape);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void submit(long groupId, Callable<String> msg, boolean autoEscape, ServerPlayer player) {
        executor.submit(() -> {
            try {
                val message = msg.call();
                Const.LOGGER.debug("转发游戏消息: {}", message);
                MinecraftForge.EVENT_BUS.post(new BotChatEvent(player,
                        IMcBot.onebot.getBot().sendGroupMsg(groupId, message, autoEscape).getData().getMessageId(),
                        message
                ));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void submit(ActionPath action, JsonObject params) {
        Const.LOGGER.info("执行自定义操作：{}", action);
        executor.submit(() -> IMcBot.onebot.getBot().customRequest(action, params));
    }

    public void register(Runnable callable) {
        executor.submit(callable);
    }

    public void stop() {
        executor.shutdownNow();
    }
}
