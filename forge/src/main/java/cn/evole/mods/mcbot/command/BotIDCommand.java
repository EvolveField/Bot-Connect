package cn.evole.mods.mcbot.command;

import cn.evole.mods.mcbot.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#if MC <11900
import net.minecraft.network.chat.TextComponent;
//#endif

public class BotIDCommand {


    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        long id = context.getArgument("BotId", Long.class);
        ModConfig.INSTANCE.getBotConfig().setBotId(id);
        //#if MC >= 12000
        //$$ context.getSource().sendSuccess(()->Component.literal("已设置机器人QQ号为:" + id + "！"), true);
        //#elseif MC < 11900
        context.getSource().sendSuccess(new TextComponent("已设置机器人QQ号为:" + id + "！"), true);
        //#else
        //$$ context.getSource().sendSuccess(Component.literal("已设置机器人QQ号为:" + id + "！"), true);
        //#endif
        ModConfig.INSTANCE.save();
        return 1;
    }


}
