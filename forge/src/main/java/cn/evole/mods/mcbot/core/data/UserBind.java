package cn.evole.mods.mcbot.core.data;

import com.xykj.easycsv.entity.CsvProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class UserBind {
    @CsvProperty("添加日期")
    private long createTime = 0L;
    @CsvProperty("qq")
    private String qqId = "";
    @CsvProperty("群号")
    private String groupId = "";
    @CsvProperty("游戏名")
    private String gameName = "";
}
