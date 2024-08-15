package cn.evole.mods.mcbot.common.command;


import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

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
            ModConfig.get().getCommon().setEnable(false);
            ModConfig.save();
        }
        return 1;
    }
}
