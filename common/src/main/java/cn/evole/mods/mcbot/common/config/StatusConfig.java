package cn.evole.mods.mcbot.common.config;

import com.iafenvoy.jupiter.config.container.AutoInitConfigContainer.AutoInitConfigCategoryBase;
import com.iafenvoy.jupiter.config.entry.BooleanEntry;
import lombok.Getter;
import lombok.Setter;

/**
 * Name: McBot-fabric / StatusConfig
 * Author: cnlimiter
 * CreateTime: 2023/11/7 18:49
 * Description:
 */

@Getter
@Setter
public class StatusConfig extends AutoInitConfigCategoryBase {
    //连接的消息开关
    public BooleanEntry connectInfoEnable = new BooleanEntry("config.mcbot.status.connectInfoEnable", true);
    //接收来自q群的消息开关
    public BooleanEntry rEnable = new BooleanEntry("config.mcbot.status.rEnable", true);
    public BooleanEntry rCmdEnable = new BooleanEntry("config.mcbot.status.rCmdEnable", true);
    public BooleanEntry rChatEnable = new BooleanEntry("config.mcbot.status.rChatEnable", true);

    //发往q群消息的开关
    public BooleanEntry sEnable = new BooleanEntry("config.mcbot.status.sEnable", true);
    public BooleanEntry sQqWelcomeEnable = new BooleanEntry("config.mcbot.status.sQqWelcomeEnable", true);
    public BooleanEntry sQqLeaveEnable = new BooleanEntry("config.mcbot.status.sQqLeaveEnable", true);
    public BooleanEntry sJoinEnable = new BooleanEntry("config.mcbot.status.sJoinEnable", true);
    public BooleanEntry sLeaveEnable = new BooleanEntry("config.mcbot.status.sLeaveEnable", true);
    public BooleanEntry sDeathEnable = new BooleanEntry("config.mcbot.status.sDeathEnable", true);
    public BooleanEntry sChatEnable = new BooleanEntry("config.mcbot.status.sChatEnable", true);
    public BooleanEntry sAdvanceEnable = new BooleanEntry("config.mcbot.status.sAdvanceEnable", true);

    public StatusConfig() {
        super("status", "config.mcbot.category.status");
    }
}
