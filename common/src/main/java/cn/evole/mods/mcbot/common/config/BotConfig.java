package cn.evole.mods.mcbot.common.config;

import lombok.Getter;
import lombok.Setter;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

/**
 * Name: McBot-fabric / BotConfig
 * Author: cnlimiter
 * CreateTime: 2023/11/7 20:28
 * Description:
 */

@Getter
@Setter
@ConfigSerializable
public class BotConfig {
    @Comment("跨服支持,权限支持")
    private String tag = "main";
    @Comment("地址（支持域名和ipv6）")
    private String url = "ws://127.0.0.1:8080";
    @Comment("鉴权")
    private String token = "";
    @Comment("机器人qq")
    private long botId = 0L;//机器人qq
    @Comment("自动重连")
    private boolean reconnect = false;
    @Comment("自动重连次数")
    private int maxReconnectAttempts = 3;
    @Comment("超时宽容度（毫秒）")
    private long timeoutCompensation = 1000;

    public cn.evole.onebot.client.core.BotConfig build() {
        return new cn.evole.onebot.client.core.BotConfig(url, token, botId, token.startsWith("mirai_"), reconnect, maxReconnectAttempts);
    }

}
