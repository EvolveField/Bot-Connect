package cn.evole.mods.mcbot.common.config;

import com.google.common.collect.ImmutableList;
import com.iafenvoy.jupiter.config.container.AutoInitConfigContainer.AutoInitConfigCategoryBase;
import com.iafenvoy.jupiter.config.entry.BooleanEntry;
import com.iafenvoy.jupiter.config.entry.ListStringEntry;
import com.iafenvoy.jupiter.config.entry.StringEntry;
import lombok.Getter;
import lombok.Setter;

/**
 * Name: McBot-fabric / CommonConfig
 * Author: cnlimiter
 * CreateTime: 2023/11/7 20:24
 * Description:
 */
@Getter
@Setter
public class CommonConfig extends AutoInitConfigCategoryBase {

    public BooleanEntry groupOn = new BooleanEntry("config.mcbot.common.groupOn", true);
    public ListStringEntry groupIdList = new ListStringEntry("config.mcbot.common.groupIdList", ImmutableList.<String>builder().build());
    public BooleanEntry enable = new BooleanEntry("config.mcbot.common.enable", true);
    public BooleanEntry debug = new BooleanEntry("config.mcbot.common.debug", false);
    public StringEntry languageSelect = new StringEntry("config.mcbot.common.languageSelect", "zh_cn");
    public BooleanEntry autoOpen = new BooleanEntry("config.mcbot.common.autoOpen", true);
    public BooleanEntry imageOn = new BooleanEntry("config.mcbot.common.imageOn", true);
    public BooleanEntry bindOn = new BooleanEntry("config.mcbot.common.bindOn", false);

    public CommonConfig() {
        super("common", "config.mcbot.category.common");
    }

    public void removeGroupId(long id) {
        groupIdList.getValue().remove(String.valueOf(id));
    }

    public void addGroupId(long id) {
        if (!groupIdList.getValue().contains(String.valueOf(id))) groupIdList.getValue().add(String.valueOf(id));
    }

}
