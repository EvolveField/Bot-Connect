package cn.evole.mods.mcbot.api.cmd;

import lombok.Getter;

import java.util.List;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/14 13:04
 * @Description:
 */
@Getter
public class Cmd {
    private String cmd;
    private List<String> alies;
    private List<String> allow_members;
    private String permission;
}
