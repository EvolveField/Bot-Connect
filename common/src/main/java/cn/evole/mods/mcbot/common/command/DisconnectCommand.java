package cn.evole.mods.mcbot.common.command;


import cn.evole.mods.mcbot.Constants;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#endif

public class DisconnectCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if (Constants.onebot != null) {
            Constants.onebot.close();
            Constants.connected = false;
            if (!Constants.onebot.getWs().isOpen()) {
                context.getSource().sendSuccess(() -> Component.literal("WebSocket已断开连接"), true);
            } else {
                context.getSource().sendSuccess(() -> Component.literal("WebSocket目前未连接"), true);
            }
            Constants.configManager.config().getCommon().setEnable(false);
        }
        return 1;
    }
}
