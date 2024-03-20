package cn.evole.mods.mcbot.core.event;

import cn.evole.mods.mcbot.Const;
import cn.evole.mods.mcbot.core.data.UserBindApi;
import cn.evole.mods.mcbot.config.ModConfig;
import cn.evole.mods.mcbot.util.locale.I18n;
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
import net.minecraft.network.chat.Component;
//#if MC <11900
import net.minecraft.network.chat.TextComponent;
//#endif

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/18 9:48
 * Version: 1.0
 */
public class IPlayerEvent {
    public static void loggedIn(Level world, ServerPlayer player) {
        if (ModConfig.INSTANCE.getCommon().isBindOn() && !UserBindApi.isIn(player.getGameProfile().getName())){
            //#if MC >= 11900
            //$$ val toSend = Component.literal("请先完成绑定(爱来自群服互联~)");
            //#else
            val toSend = new TextComponent("请先完成绑定(爱来自群服互联~)");
            //#endif
            player.connection.disconnect(toSend);
            return;//防止冗余消息
        }

        if (ModConfig.INSTANCE.getStatus().isSJoinEnable() && ModConfig.INSTANCE.getStatus().isSEnable()) {
            val msg = player.getDisplayName().getString() + " 加入了服务器";
            send(msg, world);
        }
    }
    public static void loggedOut(Level world, ServerPlayer player) {
        if (ModConfig.INSTANCE.getCommon().isBindOn() && !UserBindApi.isIn(player.getGameProfile().getName())){
            return;//防止冗余消息
        }
        if (ModConfig.INSTANCE.getStatus().isSLeaveEnable() && ModConfig.INSTANCE.getStatus().isSEnable()) {
            val msg = player.getDisplayName().getString() + " 离开了服务器";
            send(msg, world);
        }
    }
    public static void death(DamageSource source, ServerPlayer player) {
        if (player != null && ModConfig.INSTANCE.getStatus().isSDeathEnable() && ModConfig.INSTANCE.getStatus().isSEnable()) {
            LivingEntity livingEntity2 = player.getKillCredit();
            String message = "";

            //#if MC >= 11904
            //$$ String string = "mcbot.death.attack." + source.type().msgId();
            //#else
            String string = "mcbot.death.attack." + source.getMsgId();
            //#endif

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
            send(msg, player.getCommandSenderWorld());
        }
    }

    public static void advancement(Player player, Advancement advancement) {
        //#if MC <= 12001
        boolean displayExist = advancement.getDisplay() != null;
        //#else
        //$$ boolean displayExist = advancement.display().isPresent();
        //#endif

        if (ModConfig.INSTANCE.getStatus().isSAdvanceEnable() && displayExist && ModConfig.INSTANCE.getStatus().isSEnable()) {
            //#if MC <= 12001
            DisplayInfo display = advancement.getDisplay();
            //#else
            //$$ DisplayInfo display = advancement.display().get();
            //#endif

            //#if MC > 12001
            //$$ String message = I18n.get("mcbot.chat.type.advancement." + display.getType().getSerializedName(), player.getDisplayName().getString(), I18n.get(display.getTitle().getString()));
            //#else
            String message = I18n.get("mcbot.chat.type.advancement." + display.getFrame().getName(), player.getDisplayName().getString(), I18n.get(display.getTitle().getString()));
            //#endif

            val msg = String.format(message, player.getDisplayName().getString());
            send(msg, player.getCommandSenderWorld());
        }
    }

    private static void send(String msg, Level level){
        if (!level.isClientSide)  Const.sendAllGroupMsg(msg);
    }

}
