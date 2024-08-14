package cn.evole.mods.mcbot.core.cmd;

import cn.evole.mods.mcbot.api.cmd.Cmd;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/14 13:19
 * @Description:
 */
public class CmdHandler {

    public static List<Cmd> cmds = new ArrayList<>();

    public static void load() {
        Yaml yaml = new Yaml();
    }

}
