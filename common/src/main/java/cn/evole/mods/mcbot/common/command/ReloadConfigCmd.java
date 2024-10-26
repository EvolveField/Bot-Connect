package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/28 13:37
 * Version: 1.0
 */
public class ReloadConfigCmd {
    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        try {
            //Constants.configManager..reload();
//            if (Constants.configManager.config() == null) {
//                context.getSource().sendSuccess(() -> Component.literal("重载配置失败"), true);
//            }
            context.getSource().sendSuccess(() -> Component.literal("重载配置成功"), true);
        } catch (Exception e) {
            context.getSource().sendSuccess(() -> Component.literal("重载配置失败"), true);
        }
        ModConfig.get().save();
        return 1;
    }
}
