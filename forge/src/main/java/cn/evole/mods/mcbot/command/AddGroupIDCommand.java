package cn.evole.mods.mcbot.command;

import cn.evole.mods.mcbot.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.val;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#if MC <11900
import net.minecraft.network.chat.TextComponent;
//#endif

public class AddGroupIDCommand {


    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        val id = context.getArgument("GroupId", Long.class);
        if (ModConfig.INSTANCE.getCommon().getGroupIdList().contains(id)) {
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("QQ群号:" + id + "已经出现了！"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("QQ群号:" + id + "已经出现了！"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("QQ群号:" + id + "已经出现了！"), true);
            //#endif
        } else {
            ModConfig.INSTANCE.getCommon().addGroupId(id);
            //#if MC >= 12000
            //$$ context.getSource().sendSuccess(()->Component.literal("已成功添加QQ群号:" + id + "！"), true);
            //#elseif MC < 11900
            context.getSource().sendSuccess(new TextComponent("已成功添加QQ群号:" + id + "！"), true);
            //#else
            //$$ context.getSource().sendSuccess(Component.literal("已成功添加QQ群号:" + id + "！"), true);
            //#endif
        }
        ModConfig.INSTANCE.save();
        return 1;
    }


}
