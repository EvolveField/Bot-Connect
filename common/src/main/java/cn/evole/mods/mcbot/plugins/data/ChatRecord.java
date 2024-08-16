package cn.evole.mods.mcbot.plugins.data;

import com.github.houbb.csv.annotation.Csv;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Project: McBot-fabric
 * @Author: cnlimiter
 * @CreateTime: 2024/2/21 13:59
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRecord {
    @Csv(label = "消息ID")
    private String messageId = "";
    @Csv(label = "添加日期")
    private long createTime = 0L;
    @Csv(label = "qq")
    private String qqId = "";
    @Csv(label = "群号")
    private String groupId = "";
    @Csv(label = "消息")
    private String message = "";
}
