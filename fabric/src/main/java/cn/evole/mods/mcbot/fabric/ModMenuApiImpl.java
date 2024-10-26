package cn.evole.mods.mcbot.fabric;

import cn.evole.mods.mcbot.common.config.ModConfig;
import com.iafenvoy.jupiter.screen.ConfigSelectScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.network.chat.Component;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/10/26 18:04
 * @Description:
 */
public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigSelectScreen<>(Component.translatable("config.mcbot.title"), parent, ModConfig.INSTANCE, null);
    }
}