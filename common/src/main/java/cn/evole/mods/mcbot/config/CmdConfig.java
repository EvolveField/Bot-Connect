package cn.evole.mods.mcbot.config;

import lombok.Getter;
import lombok.Setter;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

/**
 * Name: McBot-fabric / CmdConfig
 * Author: cnlimiter
 * CreateTime: 2023/11/7 20:19
 * Description:
 */

@Getter
@Setter
@ConfigSerializable
public class CmdConfig{
    @Comment("自定义q群加入事件消息")
    private String welcomeNotice = "欢迎加群~";//自定义q群加入事件消息
    @Comment("自定义q群离开消息")
    private String leaveNotice = "离开了我们qwq";//自定义q群离开消息
    @Comment("q群中使用命令的关键符号")
    private String cmdStart = "!";//q群中使用命令的关键符号

    @Comment("是否开启显示到游戏中的前缀")
    private boolean gamePrefixOn = true;//是否开启显示到游戏中的前缀
    @Comment("是否开启显示到游戏中的id前缀")
    private boolean idGamePrefixOn = true;//是否开启显示到游戏中的id前缀
    @Comment("来自q群显示到游戏中的前缀")
    private String qqGamePrefix = "群聊";//来自q群显示到游戏中的前缀
    @Comment("来自频道显示到游戏中的前缀")
    private String guildGamePrefix = "频道";//来自频道显示到游戏中的前缀
    @Comment("是否开启显示到游戏中的昵称为群昵称")
    private boolean groupNickOn = false;//是否开启显示到游戏中的昵称为群昵称


    @Comment("是否开启来自游戏的消息显示到群中的前缀")
    private boolean mcPrefixOn = true;//是否开启来自游戏的消息显示到群中的前缀
    @Comment("来自游戏的消息显示到群中的前缀")
    private String mcPrefix = "MC";//来自游戏的消息显示到群中的前缀



    @Comment("是否开启游戏中自定义关键词")
    private boolean mcChatPrefixOn = false;//是否开启游戏中自定义关键词
    @Comment("是否开启qq中自定义关键词")
    private boolean qqChatPrefixOn = false;//是否开启qq中自定义关键词
    @Comment("游戏中自定义的消息头文本")
    private String mcChatPrefix = "q";//游戏中自定义的消息头文本
    @Comment("qq中自定义的消息头文本")
    private String qqChatPrefix = "m";//qq中自定义的消息头文本

}
