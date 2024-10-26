package cn.evole.mods.mcbot.common.config;

import com.iafenvoy.jupiter.config.AutoInitConfigContainer;
import com.iafenvoy.jupiter.malilib.config.options.ConfigBoolean;
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
public class StatusConfig extends AutoInitConfigContainer.AutoInitConfigCategoryBase {
    //连接的消息开关
    public ConfigBoolean connectInfoEnable = new ConfigBoolean("config.mcbot.status.connectInfoEnable", true, "连接提醒");
    //接收来自q群的消息开关
    public ConfigBoolean rEnable = new ConfigBoolean("config.mcbot.status.rEnable", true, "全局接收");
    public ConfigBoolean rCmdEnable = new ConfigBoolean("config.mcbot.status.rCmdEnable", true, "命令接收");
    public ConfigBoolean rChatEnable = new ConfigBoolean("config.mcbot.status.rChatEnable", true, "消息接收");

    //发往q群消息的开关
    public ConfigBoolean sEnable = new ConfigBoolean("config.mcbot.status.sEnable", true, "发送消息");
    public ConfigBoolean sQqWelcomeEnable = new ConfigBoolean("config.mcbot.status.sQqWelcomeEnable", true, "发送欢迎玩家入群消息");
    public ConfigBoolean sQqLeaveEnable = new ConfigBoolean("config.mcbot.status.sQqLeaveEnable", true, "发送玩家退群消息");
    public ConfigBoolean sJoinEnable = new ConfigBoolean("config.mcbot.status.sJoinEnable", true, "发送加入服务器消息");
    public ConfigBoolean sLeaveEnable = new ConfigBoolean("config.mcbot.status.sLeaveEnable", true, "发送离开服务器消息");
    public ConfigBoolean sDeathEnable = new ConfigBoolean("config.mcbot.status.sDeathEnable", true, "发送玩家死亡消息");
    public ConfigBoolean sChatEnable = new ConfigBoolean("config.mcbot.status.sChatEnable", true, "发送服务器聊天");
    public ConfigBoolean sAdvanceEnable = new ConfigBoolean("config.mcbot.status.sAdvanceEnable", true, "发送成就消息");

    public StatusConfig() {
        super("status", "config.mcbot.category.status");
    }
}
