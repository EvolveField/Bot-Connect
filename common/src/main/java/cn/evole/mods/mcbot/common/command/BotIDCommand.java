package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#endif

public class BotIDCommand {


    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        long id = context.getArgument("BotId", Long.class);
        ModConfig.get().getBotConfig().setBotId(id);
        context.getSource().sendSuccess(() -> Component.literal("已设置机器人QQ号为:" + id + "！"), true);
        return 1;
    }


}
