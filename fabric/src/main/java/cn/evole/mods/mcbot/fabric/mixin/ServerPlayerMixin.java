package cn.evole.mods.mcbot.fabric.mixin;

import cn.evole.mods.mcbot.api.event.server.ServerGameEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/12 13:49
 * @Description:
 */
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "die(Lnet/minecraft/world/damagesource/DamageSource;)V", at = @At(value = "HEAD"))
    public void mcbot$die(DamageSource damageSource, CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer) (Object) this;

        ServerGameEvents.PLAYER_DEATH.invoker().onDeath(damageSource, player);
    }
}
