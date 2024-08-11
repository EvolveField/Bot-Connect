package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.Constants;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.val;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

//#endif
public class AuthKeyCommand {


    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        val id = context.getArgument("AuthKey", String.class);
        Constants.configManager.config().getBotConfig().setToken(id);
        context.getSource().sendSuccess(() -> Component.literal("已设置框架的AuthKey为:" + id), true);
        return 1;
    }


}
