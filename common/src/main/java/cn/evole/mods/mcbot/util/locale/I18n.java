package cn.evole.mods.mcbot.util.locale;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.common.config.ModConfig;
import cn.evole.mods.mcbot.platform.Services;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.locale.Language;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/15 20:01
 * @Description:
 */
public class I18n {
    private static Map<String, String> translations;

    public static void init() {
        translations = new HashMap<>();


        Optional<Path> optional = Services.PLATFORM.getResourcePath("lang/" + ModConfig.get().getCommon().getLanguageSelect().getStringValue() + ".json");

        if (optional.isEmpty()) {
            Constants.LOGGER.warn("-----------------------------------------");
            Constants.LOGGER.warn("McBot cannot find translations for \"" + ModConfig.get().getCommon().getLanguageSelect().getStringValue() + "\" and uses \"en_us\" by default!");
            Constants.LOGGER.warn("");
            Constants.LOGGER.warn("You are welcome to contribute translations!");
            Constants.LOGGER.warn("Contributing: https://github.com/cnlimiter/McBot#Contributing");
            Constants.LOGGER.warn("-----------------------------------------");

            optional = Services.PLATFORM.getResourcePath("/lang/en_us.json");
        }

        if (optional.isPresent()) {
            try {
                String content = IOUtils.toString(Files.newInputStream(optional.get()), StandardCharsets.UTF_8);
                translations = new Gson().fromJson(content, new TypeToken<Map<String, String>>() {
                }.getType());
            } catch (Exception e) {
                Constants.LOGGER.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    public static void reload() {
        translations = null;
        init();
    }

    public static String get(String key, Object... args) {
        try {
            String translation1 = translations.get(key);
            if (translation1 != null) {
                return String.format(translation1, args);
            } else {
                String key2 = key.replaceAll("mcbot.", "");
                String translation2 = Language.getInstance().getOrDefault(key2);
                if (!translation2.equals(key2)) {
                    return String.format(translation2, args);
                } else {
                    return "TranslateError{\"key\":\"" + key2 + "\",\"args\":" + Arrays.toString(args) + "}";
                }
            }
        } catch (Exception e) {
            return "TranslateError{\"key\":\"" + key + "\",\"args\":" + Arrays.toString(args) + "}";
        }
    }

    public static String get(String key) {
        String translation = translations.get(key);
        if (translation != null) {
            return translation;
        } else {
            return key;
        }
    }
}
