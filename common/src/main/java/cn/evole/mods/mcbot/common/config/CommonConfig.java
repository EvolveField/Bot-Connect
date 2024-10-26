package cn.evole.mods.mcbot.common.config;

import com.google.common.collect.ImmutableList;
import com.iafenvoy.jupiter.config.AutoInitConfigContainer;
import com.iafenvoy.jupiter.malilib.config.options.ConfigBoolean;
import com.iafenvoy.jupiter.malilib.config.options.ConfigString;
import com.iafenvoy.jupiter.malilib.config.options.ConfigStringList;
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
public class CommonConfig extends AutoInitConfigContainer.AutoInitConfigCategoryBase {

    public ConfigBoolean groupOn = new ConfigBoolean("config.mcbot.common.groupOn", true, "开启q群功能");
    public ConfigStringList groupIdList = new ConfigStringList("config.mcbot.common.groupIdList", ImmutableList.<String>builder().build(), "支持多个q群");
    public ConfigBoolean enable = new ConfigBoolean("config.mcbot.common.enable", true, "是否启用");
    public ConfigBoolean debug = new ConfigBoolean("config.mcbot.common.debug", false, "是否开发模式，将显示事件信息操作");
    public ConfigString languageSelect = new ConfigString("config.mcbot.common.languageSelect", "zh_cn", "选择语言系统，zh_cn为简体中文，en_us为英文");
    public ConfigBoolean autoOpen = new ConfigBoolean("config.mcbot.common.autoOpen", true, "自动连接");
    public ConfigBoolean imageOn = new ConfigBoolean("config.mcbot.common.imageOn", true, "是否开启聊天栏图片功能");
    public ConfigBoolean bindOn = new ConfigBoolean("config.mcbot.common.bindOn", false, "是否开启绑定校验");

    public CommonConfig() {
        super("common", "config.mcbot.category.common");
    }

    public void removeGroupId(long id) {
        groupIdList.getStrings().remove(String.valueOf(id));
    }

    public void addGroupId(long id) {
        if (!groupIdList.getStrings().contains(String.valueOf(id))) groupIdList.getStrings().add(String.valueOf(id));
    }

}
