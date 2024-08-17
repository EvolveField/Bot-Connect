package cn.evole.mods.mcbot.plugins.data;

import com.github.houbb.csv.annotation.Csv;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * @Project: McBot-fabric
 * @Author: cnlimiter
 * @CreateTime: 2024/2/20 14:56
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Csv(label = "添加日期")
    private long createTime = 0L;
    @Csv(label = "qq")
    private String qqId = "";
    @Csv(label = "群号")
    private String groupId = "";
    @Csv(label = "游戏名")
    private String gameName = "";
    @Csv(label = "金币")
    private long coin = 0L;
    @Csv(label = "权限")
    private List<String> permissions = Arrays
            .asList(
                    "mcbot.cmd.tps",
                    "mcbot.cmd.list"
            );
}
