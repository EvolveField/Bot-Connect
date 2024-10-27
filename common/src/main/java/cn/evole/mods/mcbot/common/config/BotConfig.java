package cn.evole.mods.mcbot.common.config;

import com.iafenvoy.jupiter.config.AutoInitConfigContainer;
import com.iafenvoy.jupiter.malilib.config.options.ConfigBoolean;
import com.iafenvoy.jupiter.malilib.config.options.ConfigInteger;
import com.iafenvoy.jupiter.malilib.config.options.ConfigString;
import lombok.Getter;
import lombok.Setter;

/**
 * Name: McBot-fabric / BotConfig
 * Author: cnlimiter
 * CreateTime: 2023/11/7 20:28
 * Description:
 */

@Getter
@Setter
public class BotConfig extends AutoInitConfigContainer.AutoInitConfigCategoryBase {
    public ConfigString tag = new ConfigString("config.mcbot.bot.tag", "main", "跨服支持,权限支持");
    public ConfigString url = new ConfigString("config.mcbot.bot.url", "127.0.0.1:18082", "地址（支持域名和ipv6）");
    public ConfigString token = new ConfigString("config.mcbot.bot.token", "", "鉴权");
    public ConfigString botId = new ConfigString("config.mcbot.bot.botId", "0", "机器人qq");
    public ConfigBoolean reconnect = new ConfigBoolean("config.mcbot.bot.reconnect", true, "自动重连");
    public ConfigInteger maxReconnectAttempts = new ConfigInteger("config.mcbot.bot.maxReconnectAttempts", 3, 0, 10, "自动重连次数");
    public ConfigInteger timeoutCompensation = new ConfigInteger("config.mcbot.bot.timeoutCompensation", 1000, 0, 10000, "超时宽容度（毫秒）");

    public BotConfig() {
        super("bot", "config.mcbot.category.bot");
    }

    public cn.evole.onebot.client.core.BotConfig build() {
        return new cn.evole.onebot.client.core.BotConfig(
                url.getStringValue().startsWith("ws://") ? url.getStringValue() : "ws://" + url.getStringValue()
                , token.getStringValue(), Long.parseLong(botId.getStringValue()), token.getStringValue().startsWith("mirai_"),
                reconnect.getBooleanValue(), maxReconnectAttempts.getIntegerValue());
    }

}
