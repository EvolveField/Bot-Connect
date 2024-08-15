package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;

public class DelBindCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String qq_id = context.getArgument("QQId", String.class);
        //UserBindApi.del(qq_id);
        ModConfig.save();
        return 1;
    }
}
