package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.Constants;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.val;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
//#endif

public class AddGroupIDCommand {


    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        val id = context.getArgument("GroupId", Long.class);
        if (Constants.configManager.config().getCommon().getGroupIdList().contains(id)) {
            context.getSource().sendSuccess(() -> Component.literal("QQ群号:" + id + "已经出现了！"), true);

        } else {
            Constants.configManager.config().getCommon().addGroupId(id);
            context.getSource().sendSuccess(() -> Component.literal("已成功添加QQ群号:" + id + "！"), true);
        }
        return 1;
    }


}
