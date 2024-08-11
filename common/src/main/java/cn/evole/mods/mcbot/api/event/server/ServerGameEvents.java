package cn.evole.mods.mcbot.api.event.server;

import cn.evole.mods.mcbot.api.event.ToggleableEvent;
import net.minecraft.advancements.Advancement;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/18 9:36
 * Version: 1.0
 */
public final class ServerGameEvents {
    public static final ToggleableEvent<PlayerTick> PLAYER_TICK = ToggleableEvent.create(PlayerTick.class, callbacks -> (world, player) -> {
        for (PlayerTick callback : callbacks) {
            callback.onTick(world, player);
        }
    });

    public static final ToggleableEvent<PlayerDeath> PLAYER_DEATH = ToggleableEvent.create(PlayerDeath.class, callbacks -> (source, player) -> {
        for (PlayerDeath callback : callbacks) {
            callback.onDeath(source, player);
        }
    });

    public static final ToggleableEvent<PlayerChangeDimension> PLAYER_CHANGE_DIMENSION = ToggleableEvent.create(PlayerChangeDimension.class, callbacks -> (world, player) -> {
        for (PlayerChangeDimension callback : callbacks) {
            callback.onChangeDimension(world, player);
        }
    });

    public static final ToggleableEvent<PlayerDigSpeedCalc> ON_PLAYER_DIG_SPEED_CALC = ToggleableEvent.create(PlayerDigSpeedCalc.class, callbacks -> (world, player, digSpeed, state) -> {
        for (PlayerDigSpeedCalc callback : callbacks) {
            float newSpeed = callback.onDigSpeedCalc(world, player, digSpeed, state);
            if (newSpeed != digSpeed) {
                return newSpeed;
            }
        }

        return -1;
    });

    public static final ToggleableEvent<PlayerLoggedIn> PLAYER_LOGGED_IN = ToggleableEvent.create(PlayerLoggedIn.class, callbacks -> (server, player) -> {
        for (PlayerLoggedIn callback : callbacks) {
            callback.onPlayerLoggedIn(server, player);
        }
    });

    public static final ToggleableEvent<PlayerLoggedOut> PLAYER_LOGGED_OUT = ToggleableEvent.create(PlayerLoggedOut.class, callbacks -> (server, player) -> {
        for (PlayerLoggedOut callback : callbacks) {
            callback.onPlayerLoggedOut(server, player);
        }
    });

    public static final ToggleableEvent<PlayerAdvancement> PLAYER_ADVANCEMENT = ToggleableEvent.create(PlayerAdvancement.class, callbacks -> (player, advancement) -> {
        for (PlayerAdvancement callback : callbacks) {
            callback.onAdvancement(player, advancement);
        }
    });


    public static final ToggleableEvent<ServerChat> SERVER_CHAT = ToggleableEvent.create(ServerChat.class, callbacks -> (player, message) -> {
        for (ServerChat callback : callbacks) {
            callback.onChat(player, message);
        }
    });

    @FunctionalInterface
    public interface PlayerAdvancement {
        void onAdvancement(Player player, Advancement advancement);
    }

    @FunctionalInterface
    public interface PlayerTick {
        void onTick(ServerLevel world, ServerPlayer player);
    }

    @FunctionalInterface
    public interface PlayerDeath {
        void onDeath(DamageSource source, ServerPlayer player);
    }

    @FunctionalInterface
    public interface PlayerChangeDimension {
        void onChangeDimension(ServerLevel world, ServerPlayer player);
    }

    @FunctionalInterface
    public interface PlayerDigSpeedCalc {
        float onDigSpeedCalc(Level world, Player player, float digSpeed, BlockState state);
    }

    @FunctionalInterface
    public interface PlayerLoggedIn {
        void onPlayerLoggedIn(MinecraftServer server, ServerPlayer player);
    }

    @FunctionalInterface
    public interface PlayerLoggedOut {
        void onPlayerLoggedOut(MinecraftServer server, ServerPlayer player);
    }

    @FunctionalInterface
    public interface ServerChat {
        void onChat(ServerPlayer player, String message);
    }
}
