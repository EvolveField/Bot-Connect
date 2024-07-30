package cn.evole.mods.mcbot.config;

import lombok.Getter;
import lombok.Setter;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import static cn.evole.mods.mcbot.Const.LOGGER;
import static cn.evole.mods.mcbot.McBot.CONFIG_FILE;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/2 13:44
 * Version: 1.0
 */

@Getter
@Setter
@ConfigSerializable
public class ModConfig{
    private static ModConfig modConfig = new ModConfig();
    private static CommentedConfigurationNode rootNode;
    public static HoconConfigurationLoader loader = HoconConfigurationLoader.builder().path(CONFIG_FILE).build();
    public static ModConfig INSTANCE(){
        return modConfig;
    }
    
    @Comment("通用")
    private CommonConfig common = new CommonConfig();
    @Comment("状态")
    private StatusConfig status = new StatusConfig();
    @Comment("命令")
    private CmdConfig cmd = new CmdConfig();
    @Comment("机器人")
    private BotConfig botConfig = new BotConfig();


    public static void load() throws Exception {
        LOGGER.info("加载配置文件...");
        rootNode = loader.load();
        if (!CONFIG_FILE.toFile().exists()) {
            LOGGER.info("没有找到配置文件，重新生成!");
            rootNode.set(ModConfig.class, new ModConfig());
            loader.save(rootNode);
        }
        modConfig = rootNode.get(ModConfig.class, new ModConfig());
    }

    public static void save(){
        try {
            loader.save(rootNode);
        } catch (ConfigurateException e) {
            LOGGER.error("配置保存错误...");
        }
    }

    public static void reload() throws Exception {
        loader.save(rootNode);
        modConfig = rootNode.get(ModConfig.class, new ModConfig());
    }

}
