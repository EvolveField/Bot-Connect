package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.Constants;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#endif

public class ReConnectCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        Constants.configManager.config().getBotConfig().setReconnect(isEnabled);
        if (isEnabled) {
            context.getSource().sendSuccess(() -> Component.literal("已设置自动重连"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("已关闭自动重连"), true);
        }
        return 1;
    }
}
