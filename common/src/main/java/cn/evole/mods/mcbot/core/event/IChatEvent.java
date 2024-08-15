package cn.evole.mods.mcbot.core.event;

import cn.evole.mods.mcbot.api.bot.BotApi;
import cn.evole.mods.mcbot.common.config.ModConfig;
import cn.evole.mods.mcbot.util.onebot.CQUtils;
import cn.evole.onebot.sdk.util.MsgUtils;
import lombok.val;
import net.minecraft.server.level.ServerPlayer;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/18 10:37
 * Version: 1.0
 */
public class IChatEvent {
    public static void register(ServerPlayer player, String message) {
        val split = message.split(" ");
        if (
                ModConfig.get().getStatus().isSChatEnable()
                        && ModConfig.get().getStatus().isSEnable()
                        && !message.contains("CICode")
                        && !player.getCommandSenderWorld().isClientSide
        ) {
            String msg = String.format(ModConfig.get().getCmd().isMcPrefixOn()
                            ? "[" + ModConfig.get().getCmd().getMcPrefix() + "]<%s> %s"
                            : "<%s> %s",
                    player.getDisplayName().getString(),
                    ModConfig.get().getCmd().isMcChatPrefixOn()
                            && ModConfig.get().getCmd().getMcChatPrefix().equals(split[0]) ? split[1] : message);

            BotApi.sendAllGroupMsg(() -> MsgUtils.builder().text(CQUtils.replace(msg)).build(), player);

        }
    }

}
