package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.api.data.UserInfoApi;
import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class DelBindCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String group_id = String.valueOf(context.getArgument("GroupId", Long.class));
        String qq_id = String.valueOf(context.getArgument("QQId", Long.class));
        if (UserInfoApi.get(group_id, qq_id) != null) {
            UserInfoApi.del(group_id, qq_id);
            context.getSource().sendSuccess(() -> Component.literal("群: "+ group_id + " 的用户: " + qq_id + " 删除成功！"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("群: "+ group_id + " 的用户: " + qq_id + " 未找到！"), true);
        }
        return 1;
    }
}
