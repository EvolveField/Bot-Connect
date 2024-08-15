package cn.evole.mods.mcbot.common.command;


import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.api.connect.ConnectApi;
import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.val;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.util.regex.Pattern;
//#endif

public class ConnectCommand {
    private static final Pattern ipv4Pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)");
    private static final Pattern ipv6Pattern = Pattern.compile("\\[([0-9a-fA-F:]+)]:(\\d+)");

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        val parameter = context.getArgument("parameter", String.class);

        if (ipv4Pattern.matcher(parameter).find() || ipv6Pattern.matcher(parameter).find()) {
            ModConfig.get().getBotConfig().setUrl(String.format("ws://%s", parameter));
            doConnect(context);
            return 1;
        } else {
            context.getSource().sendSuccess(() -> Component.literal("▌ " + ChatFormatting.RED + "参数错误❌"), true);
            return 0;
        }
    }


    public static int commonExecute(CommandContext<CommandSourceStack> context) {
        doConnect(context);
        return 1;
    }

    public static void doConnect(CommandContext<CommandSourceStack> context) {
        if (!Constants.onebot.getWs().isOpen()) {
            context.getSource().sendSuccess(() -> Component.literal("▌ " + ChatFormatting.LIGHT_PURPLE + "尝试链接框架"), true);
            ConnectApi.wsConnect();
            ModConfig.save();
        } else {
            context.getSource().sendSuccess(() -> Component.literal("▌ " + ChatFormatting.LIGHT_PURPLE + "已存在WS连接"), true);
        }
    }
}
