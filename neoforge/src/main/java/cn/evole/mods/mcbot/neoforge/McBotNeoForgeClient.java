package cn.evole.mods.mcbot.neoforge;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.iafenvoy.jupiter.render.screen.ConfigSelectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jetbrains.annotations.NotNull;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/10/27 03:21
 * @Description:
 */
@EventBusSubscriber(
        bus = EventBusSubscriber.Bus.MOD,
        value = {Dist.CLIENT}
)
public class McBotNeoForgeClient {
    public McBotNeoForgeClient() {
    }

    @SubscribeEvent
    public static void processClient(FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> new IConfigScreenFactory() {
            @Override
            public @NotNull Screen createScreen(@NotNull ModContainer modContainer, @NotNull Screen parent) {
                return new ConfigSelectScreen<>(Component.translatable("config.mcbot.title"), parent, ModConfig.INSTANCE, null);
            }
        });
    }

}
