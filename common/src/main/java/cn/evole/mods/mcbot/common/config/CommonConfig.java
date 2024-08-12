package cn.evole.mods.mcbot.common.config;

import lombok.Getter;
import lombok.Setter;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: McBot-fabric / CommonConfig
 * Author: cnlimiter
 * CreateTime: 2023/11/7 20:24
 * Description:
 */
@Getter
@Setter
@ConfigSerializable
public class CommonConfig {

    @Comment("开启q群功能")
    private boolean groupOn = true;
    @Comment("支持多个q群")
    private List<Long> groupIdList = new ArrayList<Long>() {
    };//支持多个q群
    @Comment("是否启用")
    private boolean enable = true;//是否启用
    @Comment("是否开发模式，将显示事件信息操作")
    private boolean debug = false;//是否开发模式，将显示事件信息操作
    @Comment("选择语言系统")
    private String languageSelect = "zh_cn";//选择语言系统
    @Comment("自动连接")
    private boolean autoOpen = true;//自动连接
    @Comment("是否开启聊天栏图片功能")
    private boolean imageOn = true;//是否开启聊天栏图片功能
    @Comment("是否开启绑定校验")
    private boolean bindOn = false;//是否开启绑定校验
    public void removeGroupId(long id) {
        groupIdList.remove(id);
    }

    public void addGroupId(long id) {
        if (!groupIdList.contains(id)) groupIdList.add(id);
    }

}
