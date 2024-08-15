package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.val;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class AuthKeyCommand {


    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        val id = context.getArgument("AuthKey", String.class);
        ModConfig.get().getBotConfig().setToken(id);
        context.getSource().sendSuccess(() -> Component.literal("已设置框架的AuthKey为:" + id), true);
        ModConfig.save();
        return 1;
    }


}
