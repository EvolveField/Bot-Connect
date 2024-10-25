package cn.evole.mods.mcbot.api.cmd;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/14 13:04
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Cmd {
    @Expose private String id;
    @Expose private String cmd;
    @Expose private List<String> alies;
    @Expose private List<String> allow_members;
    @Expose private String permission;
    @Expose private List<String> after_cmds;
    @Expose private String answer;
    @Expose private boolean enable;
}
