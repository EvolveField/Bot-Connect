package cn.evole.mods.mcbot.common.config;

import com.iafenvoy.jupiter.config.AutoInitConfigContainer;
import com.iafenvoy.jupiter.malilib.config.options.ConfigBoolean;
import com.iafenvoy.jupiter.malilib.config.options.ConfigString;
import lombok.Getter;
import lombok.Setter;

/**
 * Name: McBot-fabric / CmdConfig
 * Author: cnlimiter
 * CreateTime: 2023/11/7 20:19
 * Description:
 */

@Getter
@Setter
public class CmdConfig extends AutoInitConfigContainer.AutoInitConfigCategoryBase {
    public ConfigString welcomeNotice = new ConfigString("config.mcbot.cmd.welcomeNotice", "欢迎加群~", "自定义q群加入事件消息");
    public ConfigString leaveNotice = new ConfigString("config.mcbot.cmd.leaveNotice", "离开了我们qwq", "自定义q群离开事件消息");
    public ConfigString cmdStart = new ConfigString("config.mcbot.cmd.cmdStart", "!", "自定义q群使用命令的关键符号");
    public ConfigBoolean gamePrefixOn = new ConfigBoolean("config.mcbot.cmd.gamePrefixOn", true, "是否开启显示到游戏中的前缀");
    public ConfigBoolean idGamePrefixOn = new ConfigBoolean("config.mcbot.cmd.idGamePrefixOn", true, "是否开启显示到游戏中的id前缀");
    public ConfigString qqGamePrefix = new ConfigString("config.mcbot.cmd.qqGamePrefix", "群聊", "来自q群显示到游戏中的前缀");
    public ConfigString guildGamePrefix = new ConfigString("config.mcbot.cmd.guildGamePrefix", "频道", "来自频道显示到游戏中的前缀");
    public ConfigBoolean groupNickOn = new ConfigBoolean("config.mcbot.cmd.groupNickOn", true, "是否开启来自群聊显示到游戏中的昵称为群昵称");


    public ConfigBoolean mcPrefixOn = new ConfigBoolean("config.mcbot.cmd.mcPrefixOn", true, "是否开启来自游戏的消息显示到群中的前缀");
    public ConfigString mcPrefix = new ConfigString("config.mcbot.cmd.mcPrefix", "MC", "来自游戏的消息显示到群中的前缀");


    public ConfigBoolean mcChatPrefixOn = new ConfigBoolean("config.mcbot.cmd.mcChatPrefixOn", false, "是否开启游戏中自定义关键词");
    public ConfigBoolean qqChatPrefixOn = new ConfigBoolean("config.mcbot.cmd.qqChatPrefixOn", false, "是否开启qq中自定义关键词");
    public ConfigString mcChatPrefix = new ConfigString("config.mcbot.cmd.mcChatPrefix", "q", "游戏中自定义的消息头文本");
    public ConfigString qqChatPrefix = new ConfigString("config.mcbot.cmd.qqChatPrefix", "m", "qq中自定义的消息头文本");

    public CmdConfig() {
        super("cmd", "config.mcbot.category.cmd");
    }
}
