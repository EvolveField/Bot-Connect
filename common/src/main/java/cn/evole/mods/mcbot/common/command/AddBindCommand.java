package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;

public class AddBindCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String group_name = context.getArgument("GroupName", String.class);
        String qq_id = context.getArgument("QQId", String.class);
        String game_name = context.getArgument("GameName", String.class);
        //UserBindApi.add(group_name, qq_id, game_name);
        ModConfig.save();
        return 1;
    }
}
