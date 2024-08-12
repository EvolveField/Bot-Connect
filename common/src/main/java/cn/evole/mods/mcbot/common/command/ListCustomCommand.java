package cn.evole.mods.mcbot.common.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#endif

public class ListCustomCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        StringBuilder out = new StringBuilder();
//        for (String s : CustomCmdHandler.INSTANCE.getCustomCmdMap().keySet()) {
//            out.append(s).append("\n");
//        }
        context.getSource().sendSuccess(() -> Component.literal(out.toString()), true);
        return 1;
    }
}
