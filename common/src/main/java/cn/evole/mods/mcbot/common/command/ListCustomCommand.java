package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#endif

public class ListCustomCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        //        for (String s : CustomCmdHandler.INSTANCE.getCustomCmdMap().keySet()) {
//            out.append(s).append("\n");
//        }
        context.getSource().sendSuccess(() -> Component.literal(""), true);
        ModConfig.save();
        return 1;
    }
}
