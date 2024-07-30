package cn.evole.mods.mcbot.core.event;

import cn.evole.mods.mcbot.Const;
import cn.evole.mods.mcbot.config.ConfigManager;
import cn.evole.mods.mcbot.util.onebot.CQUtils;
import cn.evole.onebot.sdk.util.MsgUtils;
import lombok.val;
import net.minecraft.server.level.ServerPlayer;
import cn.evole.mods.mcbot.init.compat.vanish.VanishAPI;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/18 10:37
 * Version: 1.0
 */
public class IChatEvent {
    public static void register(ServerPlayer player, String message) {
        if (VanishAPI.isVanished(player)) return;

        val split = message.split(" ");
        if (ConfigManager.instance() != null
                && ConfigManager.instance().getStatus().isSChatEnable()
                && ConfigManager.instance().getStatus().isSEnable()
                && !message.contains("CICode")
                && !player.getCommandSenderWorld().isClientSide
        ) {
            String msg = String.format(ConfigManager.instance().getCmd().isMcPrefixOn()
                            ? "[" + ConfigManager.instance().getCmd().getMcPrefix() + "]<%s> %s"
                            : "<%s> %s",
                    player.getDisplayName().getString(),
                    ConfigManager.instance().getCmd().isMcChatPrefixOn()
                            && ConfigManager.instance().getCmd().getMcChatPrefix().equals(split[0]) ? split[1] : message);

            Const.sendAllGroupMsg(() -> MsgUtils.builder().text(CQUtils.replace(msg)).build(), player);

        }
    }

}
