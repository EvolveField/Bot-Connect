package cn.evole.mods.mcbot.util.onebot;

import cn.evole.mods.mcbot.common.config.ModConfig;
import cn.evole.mods.mcbot.platform.Services;
import cn.evole.onebot.sdk.entity.ArrayMsg;
import cn.evole.onebot.sdk.enums.MsgType;
import cn.evole.onebot.sdk.event.message.GroupMessageEvent;
import cn.evole.onebot.sdk.event.message.MessageEvent;
import cn.evole.onebot.sdk.util.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static cn.evole.mods.mcbot.Constants.*;

/**
 * Project: Bot-Connect-fabric-1.18
 * Author: cnlimiter
 * Date: 2023/2/10 1:11
 * Description:
 */
public class CQUtils {

    /**
     * @param timeout 超时时间（毫秒），超时后返回空字符串。
     */
    public static @NotNull String replace(@NotNull MessageEvent event, long timeout) {
        String back = "";
        val task = new FutureTask<>(() -> doReplace(event));
        try {
            cqExecutor.execute(task);
            back = task.get(timeout, TimeUnit.MILLISECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException | IllegalStateException e) {
            task.cancel(true);
            LOGGER.error(e.getLocalizedMessage());
        }
        return back;
    }

    private static @NotNull String doReplace(MessageEvent event) {
        val stringMsg = event.getMessage();
        val message = new StringBuilder();
        List<ArrayMsg> msg = msgToArray(stringMsg);
        for (ArrayMsg arrayMsg : msg){
            try {
                switch (arrayMsg.getType()){
                    case text -> {
                        message.append(arrayMsg.getData().get("text"));
                    }
                    case image -> {
                        val url = arrayMsg.getData().get("file");
                        if (ModConfig.get().getCommon().isImageOn() && Services.PLATFORM.isModLoaded("chatimage")) {
                            message.append(String.format("[[CICode,url=%s,name=来自QQ的图片]]",
                                    url.replaceAll("&amp;", "&")//转义字符转义
                            ));
                        } else {
                            message.append("[图片]");
                        }
                    }
                    case at -> {
                        val qq = arrayMsg.getData().get("qq");
                        if (!qq.equalsIgnoreCase("@")) {
                            if (event instanceof GroupMessageEvent groupMessageEvent)
                                message.append(String.format("[@%s]", groupMessageEvent.getSender().getNickname()));
                            else message.append("[@]");
                        } else {
                            message.append("[@全体]");
                        }
                    }
                    case reply -> {

                    }
                    default -> message.append("[?]");
                }
            } catch (Exception e) {
                LOGGER.error(e.getLocalizedMessage());
                return stringMsg;
            }
        }
        return message.toString();
    }

    private static List<ArrayMsg> msgToArray(String msg){
        JsonArray array = GsonUtils.parseArray(msg);
        List<ArrayMsg> arrayMsgList = new ArrayList<>();
        for (JsonElement e : array) {
            JsonObject object = (JsonObject) e;
            ArrayMsg arrayMsg = new ArrayMsg();
            String type = object.get("type").getAsString();
            JsonObject data = object.get("data").getAsJsonObject();
            arrayMsg.setType(MsgType.valueOf(type));
            arrayMsg.setData(new HashMap<>());
            data.asMap().forEach((k, v) -> arrayMsg.getData().put(k, v.getAsString()));
            arrayMsgList.add(arrayMsg);
        }
        return arrayMsgList;
    }

    public static void shutdown() {
        cqExecutor.shutdownNow();
    }
}
