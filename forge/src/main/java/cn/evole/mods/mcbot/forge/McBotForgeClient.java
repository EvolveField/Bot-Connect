package cn.evole.mods.mcbot.forge;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.iafenvoy.jupiter.screen.ConfigSelectScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/10/27 03:21
 * @Description:
 */
@Mod.EventBusSubscriber(
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = {Dist.CLIENT}
)
public class McBotForgeClient {
    public McBotForgeClient() {
    }

    @SubscribeEvent
    public static void processClient(FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (client, screen) ->
                                new ConfigSelectScreen<>(Component.translatable("config.mcbot.title"), screen, ModConfig.INSTANCE, null)));
    }

}
