package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.plugins.cmd.CmdHandler;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class ListCustomCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        StringBuilder out = new StringBuilder("已注册的命令：\n");
        for (String s : CmdHandler.cmds.keySet()) {
            out.append(s).append("\n");
        }
        context.getSource().sendSuccess(() -> Component.literal(out.toString()), true);
        
        return 1;
    }
}
