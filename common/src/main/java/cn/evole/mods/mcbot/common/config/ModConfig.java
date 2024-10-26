package cn.evole.mods.mcbot.common.config;

import cn.evole.mods.mcbot.Constants;
import com.google.gson.JsonObject;
import com.iafenvoy.jupiter.config.AutoInitConfigContainer;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static cn.evole.mods.mcbot.Constants.CONFIG_FOLDER;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/2 13:44
 * Version: 1.0
 */

@Getter
@Setter
public class ModConfig extends AutoInitConfigContainer {
    public static final ModConfig INSTANCE = new ModConfig();
    public static final int CURRENT_VERSION = 1;

    public CommonConfig common = new CommonConfig();
    public StatusConfig status = new StatusConfig();
    public CmdConfig cmd = new CmdConfig();
    public BotConfig botConfig = new BotConfig();

    public ModConfig() {
        super(new ResourceLocation("config.mcbot"), "config.mcbot.title", "./mcbot/config.json");
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected boolean shouldLoad(JsonObject obj) {
        int version = obj.get("version").getAsInt();
        if (version != CURRENT_VERSION && new File(this.path).exists()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                FileUtils.copyFile(new File(this.path), new File(CONFIG_FOLDER + File.separator + "config_" + sdf.format(new Date()) + ".json"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Constants.LOGGER.info("Wrong config version {} for mod {}! Automatically use version {} and backup old one.", version, Constants.MOD_NAME, CURRENT_VERSION);
            return false;
        } else Constants.LOGGER.info("{} config version match.", Constants.MOD_NAME);
        return true;
    }

    @Override
    protected void writeCustomData(JsonObject obj) {
        obj.addProperty("version", CURRENT_VERSION);
    }

    public static ModConfig get() {
        return INSTANCE;
    }

}
