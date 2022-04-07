package cn.evolvefield.mods.botapi.common.command;

import cn.evolvefield.mods.botapi.BotApi;
import cn.evolvefield.mods.botapi.common.config.ConfigManger;
import cn.evolvefield.mods.botapi.core.bot.BotData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class BotIDCommand extends CommandBase {


    private final String command;

    public BotIDCommand(String command) {
        this.command = command;
    }


    @Override
    public String getName() {
        return this.command;
    }


    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/mcbot " + this.command + "<BotId>";
    }


    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        long id;

        id = parseLong(args[0]);

        BotApi.config.getCommon().setBotId(id);
        BotData.setQQId(id);
        ConfigManger.saveBotConfig(BotApi.config);

        sender.sendMessage(new TextComponentString("已设置机器人QQ号为:" + id));


    }
}
