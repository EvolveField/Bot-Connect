package cn.evole.mods.mcbot.common.command;

import cn.evole.mods.mcbot.api.data.UserInfoApi;
import cn.evole.mods.mcbot.common.config.ModConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class AddBindCommand {

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String group_id = String.valueOf(context.getArgument("GroupId", Long.class));
        String qq_id = String.valueOf(context.getArgument("QQId", Long.class));
        String game_name = context.getArgument("GameName", String.class);
        if (UserInfoApi.get(group_id, qq_id) == null) {
            UserInfoApi.add(group_id, qq_id, game_name);
            context.getSource().sendSuccess(() -> Component.literal("群: "+ group_id + " 的用户: " + qq_id + "绑定成功！"), true);
        } else {
            context.getSource().sendSuccess(() -> Component.literal("群: "+ group_id + " 的用户: " + qq_id + "已经绑定完毕！"), true);
        }
        return 1;
    }
}
