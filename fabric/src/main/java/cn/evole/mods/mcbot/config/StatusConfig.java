package cn.evole.mods.mcbot.config;

import lombok.Getter;
import lombok.Setter;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

/**
 * Name: McBot-fabric / StatusConfig
 * Author: cnlimiter
 * CreateTime: 2023/11/7 18:49
 * Description:
 */

@Getter
@Setter
@ConfigSerializable
public class StatusConfig{
    //接收来自q群的消息开关
    @Comment("全局接收")
    private boolean rEnable = true;
    @Comment("命令接收")
    private boolean rCmdEnable = true;
    @Comment("消息接收")
    private boolean rChatEnable = true;

    //发往q群消息的开关
    @Comment("发送消息")
    private boolean sEnable = true;
    @Comment("发送欢迎玩家入群消息")
    private boolean sQqWelcomeEnable = true;
    @Comment("发送玩家退群消息")
    private boolean sQqLeaveEnable = true;
    @Comment("发送加入服务器消息")
    private boolean sJoinEnable = true;
    @Comment("发送离开服务器消息")
    private boolean sLeaveEnable = true;
    @Comment("发送玩家死亡消息")
    private boolean sDeathEnable = true;
    @Comment("发送服务器聊天")
    private boolean sChatEnable = true;
    @Comment("发送成就消息")
    private boolean sAdvanceEnable = true;
}
