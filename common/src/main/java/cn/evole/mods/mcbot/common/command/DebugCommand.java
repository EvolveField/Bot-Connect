package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.Constants;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#endif

/**
 * @author cnlimiter
 * @date 2021/11/17 13:05
 */
public class DebugCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        Constants.configManager.config().getCommon().setDebug(isEnabled);
        if (isEnabled) {
            context.getSource().sendSuccess(() -> Component.literal("已开启开发者模式"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("已关闭开发者模式"), true);
        }
        return 1;
    }
}
