package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.Constants;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.val;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

//#endif
public class DelGroupIDCommand {


    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        val id = context.getArgument("GroupID", Long.class);
        if (Constants.configManager.config().getCommon().getGroupIdList().contains(id)) {
            Constants.configManager.config().getCommon().removeGroupId(id);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("QQ群号:" + id + "并未出现！"), true);
        }
        return 1;
    }


}
