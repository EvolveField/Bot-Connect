package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class BotIDCommand {


    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        int id = context.getArgument("BotId", Integer.class);
        ModConfig.get().getBotConfig().getBotId().setValueFromString(String.valueOf(id));
        context.getSource().sendSuccess(() -> Component.literal("已设置机器人QQ号为:" + id + "！"), true);
        ModConfig.get().save();
        return 1;
    }


}
