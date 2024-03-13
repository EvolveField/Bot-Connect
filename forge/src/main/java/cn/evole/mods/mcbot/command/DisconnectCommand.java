package cn.evole.mods.mcbot.command;


import cn.evole.mods.mcbot.IMcBot;
import cn.evole.mods.mcbot.McBot;
import cn.evole.mods.mcbot.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#if MC <11900
import net.minecraft.network.chat.TextComponent;
//#endif

public class DisconnectCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if (IMcBot.onebot != null) {
            IMcBot.onebot.close();
            if (!IMcBot.onebot.getWs().isOpen()) {
                //#if MC >= 12000
                //$$ context.getSource().sendSuccess(()->Component.literal("WebSocket已断开连接"), true);
                //#elseif MC < 11900
                context.getSource().sendSuccess(new TextComponent("WebSocket已断开连接"), true);
                //#else
                //$$ context.getSource().sendSuccess(Component.literal("WebSocket已断开连接"), true);
                //#endif
            } else {
                //#if MC >= 12000
                //$$ context.getSource().sendSuccess(()->Component.literal("WebSocket目前未连接"), true);
                //#elseif MC < 11900
                context.getSource().sendSuccess(new TextComponent("WebSocket目前未连接"), true);
                //#else
                //$$ context.getSource().sendSuccess(Component.literal("WebSocket目前未连接"), true);
                //#endif
            }
            ModConfig.INSTANCE.getCommon().setEnable(false);
        }
        ModConfig.INSTANCE.save();
        return 1;
    }
}
