package cn.evole.mods.mcbot.fabric;

import cn.evole.mods.mcbot.api.config.ConfigManager;
import cn.evole.mods.mcbot.common.config.ModConfig;
import cn.evole.mods.mcbot.common.config.ModConfigScreenFactory;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/10/26 18:04
 * @Description:
 */
public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> ModConfigScreenFactory.createConfigScreen(parent, ModConfig.get(), ConfigManager.getInstance().getBaseConfig(ModConfig.class)::setAndSaveAsync);
    }
}