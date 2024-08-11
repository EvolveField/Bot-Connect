package cn.evole.mods.mcbot.core.event;

import cn.evole.mods.mcbot.api.bot.BotApi;
import lombok.val;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/18 9:48
 * Version: 1.0
 */
public class IPlayerEvent {
    public static void loggedIn(Level world, ServerPlayer player) {
        if (McBot.configManager.config().getCommon().isBindOn() && !UserBindApi.isIn(player.getGameProfile().getName())){
            val toSend = Component.literal("请先完成绑定(爱来自群服互联~)");
            player.connection.disconnect(toSend);
            return;//防止冗余消息
        }

        if (McBot.configManager.config().getStatus().isSJoinEnable() && McBot.configManager.config().getStatus().isSEnable()) {
            val msg = player.getDisplayName().getString() + " 加入了服务器";
            BotApi.sendAllGroupMsg(msg);
        }
    }
    public static void loggedOut(Level world, ServerPlayer player) {
        if (McBot.configManager.config().getCommon().isBindOn() && !UserBindApi.isIn(player.getGameProfile().getName())){
            return;//防止冗余消息
        }
        if (McBot.configManager.config().getStatus().isSLeaveEnable() && McBot.configManager.config().getStatus().isSEnable()) {
            val msg = player.getDisplayName().getString() + " 离开了服务器";
            BotApi.sendAllGroupMsg(msg);
        }
    }
    public static void death(DamageSource source, ServerPlayer player) {
        if (player != null && McBot.configManager.config().getStatus().isSDeathEnable() && McBot.configManager.config().getStatus().isSEnable()) {
            LivingEntity livingEntity2 = player.getKillCredit();
            String message = "";

            String string = "mcbot.death.attack." + source.type().msgId();

            if (source.getEntity() == null && source.getDirectEntity() == null) {
                String string2 = string + ".player";
                message = livingEntity2 != null ? I18n.get(string2, player.getDisplayName().getString(), livingEntity2.getDisplayName().getString()) : I18n.get(string, player.getDisplayName().getString());
            } else {//支持物品造成的死亡信息
                assert source.getDirectEntity() != null;
                Component component = source.getEntity() == null ? source.getDirectEntity().getDisplayName() : source.getEntity().getDisplayName();
                Entity sourceEntity = source.getEntity();
                ItemStack itemStack;
                if (sourceEntity instanceof LivingEntity) {
                    itemStack = ((LivingEntity)sourceEntity).getMainHandItem();
                } else {
                    itemStack = ItemStack.EMPTY;
                }
                message = !itemStack.isEmpty() && itemStack.hasCustomHoverName() ? I18n.get(string + ".item", player.getDisplayName().getString(), component.getString(), itemStack.getDisplayName().getString()) : I18n.get(string,player.getDisplayName().getString(), component.getString());
            }
            val msg = String.format(message, player.getDisplayName().getString());
            BotApi.sendAllGroupMsg(msg);
        }
    }

    public static void advancement(Player player, Advancement advancement) {
        boolean displayExist = advancement.getDisplay() != null;

        if (McBot.configManager.config().getStatus().isSAdvanceEnable() && displayExist && McBot.configManager.config().getStatus().isSEnable()) {
            DisplayInfo display = advancement.getDisplay();
            String message = I18n.get("mcbot.chat.type.advancement." + display.getType().getSerializedName(), player.getDisplayName().getString(), I18n.get(display.getTitle().getString()));
            val msg = String.format(message, player.getDisplayName().getString());
            BotApi.sendAllGroupMsg(msg);
        }
    }

}
