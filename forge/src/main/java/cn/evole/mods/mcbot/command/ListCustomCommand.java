package cn.evole.mods.mcbot.command;

import cn.evole.mods.mcbot.config.ModConfig;
import cn.evole.mods.mcbot.init.handler.CustomCmdHandler;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#if MC <11900
import net.minecraft.network.chat.TextComponent;
//#endif

public class ListCustomCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        StringBuilder out = new StringBuilder();
        for (String s : CustomCmdHandler.INSTANCE.getCustomCmdMap().keySet()) {
            out.append(s).append("\n");
        }
        //#if MC >= 12000
        //$$ context.getSource().sendSuccess(()->Component.literal(out.toString()), true);
        //#elseif MC < 11900
        context.getSource().sendSuccess(new TextComponent(out.toString()), true);
        //#else
        //$$ context.getSource().sendSuccess(Component.literal(out.toString()), true);
        //#endif
        ModConfig.INSTANCE.save();
        return 1;
    }
}
