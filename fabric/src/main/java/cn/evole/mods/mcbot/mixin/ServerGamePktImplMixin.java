package cn.evole.mods.mcbot.mixin;

import cn.evole.mods.mcbot.api.event.mod.McBotEvents;
import cn.evole.mods.mcbot.api.event.server.ServerGameEvents;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/12 13:51
 * @Description:
 */
@Mixin(value = ServerGamePacketListenerImpl.class, priority = 1001)
public abstract class ServerGamePktImplMixin {

    @Shadow
    public ServerPlayer player;
    @Inject(method = "broadcastChatMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/PlayerList;broadcastChatMessage(Lnet/minecraft/network/chat/PlayerChatMessage;Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/network/chat/ChatType$Bound;)V", shift = At.Shift.BEFORE), cancellable = true)
    public void mcbot$handleChat(PlayerChatMessage filteredText, CallbackInfo ci) {
        String s1 = filteredText.decoratedContent().getString();
        if (!ci.isCancelled()) McBotEvents.BEFORE_CHAT.invoker().onChat(this.player, s1, ci);
        if (!ci.isCancelled()) ServerGameEvents.SERVER_CHAT.invoker().onChat(this.player, s1);
    }
}
