package cn.evole.mods.mcbot.api.cmd;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/11 20:52
 * @Description:
 */
public class McBotCommandSource implements CommandSource {
    private static final Component RCON_COMPONENT = Component.literal("McBot");
    private final StringBuffer buffer = new StringBuffer();
    private final MinecraftServer server;

    public McBotCommandSource(MinecraftServer server) {
        this.server = server;
    }

    public void prepareForCommand() {
        this.buffer.setLength(0);
    }

    public String getCommandResponse() {
        return this.buffer.toString();
    }

    public CommandSourceStack createCommandSourceStack() {
        ServerLevel overworld = this.server.overworld();
        return new CommandSourceStack(this, Vec3.atLowerCornerOf(overworld.getSharedSpawnPos()), Vec2.ZERO, overworld, 4, "McBot", RCON_COMPONENT, this.server, null);
    }

    @Override
    public void sendSystemMessage(Component component) {
        this.buffer.append(component.getString());
    }

    @Override
    public boolean acceptsSuccess() {
        return true;
    }

    @Override
    public boolean acceptsFailure() {
        return true;
    }

    @Override
    public boolean shouldInformAdmins() {
        return false;
    }

    public String runCommand(String cmd) {
        this.prepareForCommand();
        server.executeBlocking(() -> server.getCommands().performPrefixedCommand(this.createCommandSourceStack(), cmd));
        return this.getCommandResponse();
    }

}
