package cn.evole.mods.mcbot.config;

import lombok.Getter;
import lombok.Setter;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/2 13:44
 * Version: 1.0
 */

@Getter
@Setter
@ConfigSerializable
public class ModConfig{
    @Comment("通用")
    private CommonConfig common = new CommonConfig();
    @Comment("状态")
    private StatusConfig status = new StatusConfig();
    @Comment("命令")
    private CmdConfig cmd = new CmdConfig();
    @Comment("机器人")
    private BotConfig botConfig = new BotConfig();


}
