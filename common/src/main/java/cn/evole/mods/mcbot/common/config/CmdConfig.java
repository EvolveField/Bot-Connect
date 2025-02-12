package cn.evole.mods.mcbot.common.config;

import com.iafenvoy.jupiter.config.container.AutoInitConfigContainer.AutoInitConfigCategoryBase;
import com.iafenvoy.jupiter.config.entry.BooleanEntry;
import com.iafenvoy.jupiter.config.entry.StringEntry;
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
public class CmdConfig extends AutoInitConfigCategoryBase {
    public StringEntry welcomeNotice = new StringEntry("config.mcbot.cmd.welcomeNotice", "欢迎加群~");
    public StringEntry leaveNotice = new StringEntry("config.mcbot.cmd.leaveNotice", "离开了我们qwq");
    public StringEntry cmdStart = new StringEntry("config.mcbot.cmd.cmdStart", "!");
    public BooleanEntry gamePrefixOn = new BooleanEntry("config.mcbot.cmd.gamePrefixOn", true);
    public BooleanEntry idGamePrefixOn = new BooleanEntry("config.mcbot.cmd.idGamePrefixOn", true);
    public StringEntry qqGamePrefix = new StringEntry("config.mcbot.cmd.qqGamePrefix", "群聊");
    public StringEntry guildGamePrefix = new StringEntry("config.mcbot.cmd.guildGamePrefix", "频道");
    public BooleanEntry groupNickOn = new BooleanEntry("config.mcbot.cmd.groupNickOn", true);


    public BooleanEntry mcPrefixOn = new BooleanEntry("config.mcbot.cmd.mcPrefixOn", true);
    public StringEntry mcPrefix = new StringEntry("config.mcbot.cmd.mcPrefix", "MC");


    public BooleanEntry mcChatPrefixOn = new BooleanEntry("config.mcbot.cmd.mcChatPrefixOn", false);
    public BooleanEntry qqChatPrefixOn = new BooleanEntry("config.mcbot.cmd.qqChatPrefixOn", false);
    public StringEntry mcChatPrefix = new StringEntry("config.mcbot.cmd.mcChatPrefix", "q");
    public StringEntry qqChatPrefix = new StringEntry("config.mcbot.cmd.qqChatPrefix", "m");

    public CmdConfig() {
        super("cmd", "config.mcbot.category.cmd");
    }
}
