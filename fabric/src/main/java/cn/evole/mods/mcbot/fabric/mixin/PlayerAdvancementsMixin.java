package cn.evole.mods.mcbot.fabric.mixin;

import cn.evole.mods.mcbot.api.event.server.ServerGameEvents;
import net.minecraft.advancements.Advancement;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/12 13:47
 * @Description:
 */
@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementsMixin {
    @Shadow
    private ServerPlayer player;
    @Inject(method = "award", at = @At(value = "TAIL"))
    public void mcbot$award(Advancement advancement, String string, CallbackInfoReturnable<Boolean> cir) {
        ServerPlayer player = this.player;
        if (!player.getAdvancements().getOrStartProgress(advancement).isDone()) return;
        ServerGameEvents.PLAYER_ADVANCEMENT.invoker().onAdvancement(player, advancement);
    }
}
