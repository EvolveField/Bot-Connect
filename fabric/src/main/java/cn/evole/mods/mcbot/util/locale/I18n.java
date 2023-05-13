package cn.evole.mods.mcbot.util.locale;

import cn.evole.mods.mcbot.Const;
import cn.evole.mods.mcbot.init.handler.ConfigHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.loader.api.FabricLoader;
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

public class I18n {
    private static Map<String, String> translations;

    public static void init() {
        translations = new HashMap<>();

        Optional<Path> optional = FabricLoader.getInstance().getModContainer("mcbot").orElseThrow()
                .findPath("/lang/" + ConfigHandler.cached().getCommon().getLanguageSelect() + ".json");

        if (optional.isEmpty()) {
            Const.LOGGER.warn("-----------------------------------------");
            Const.LOGGER.warn("McBot cannot find translations for \"" + ConfigHandler.cached().getCommon().getLanguageSelect() + "\" and uses \"en_us\" by default!");
            Const.LOGGER.warn("");
            Const.LOGGER.warn("You are welcome to contribute translations!");
            Const.LOGGER.warn("Contributing: https://github.com/cnlimiter/Bot-Connect#Contributing");
            Const.LOGGER.warn("-----------------------------------------");

            optional = FabricLoader.getInstance().getModContainer("mcbot").orElseThrow()
                    .findPath("/lang/en_us.json");
        }

        if (optional.isPresent()) {
            try {
                String content = IOUtils.toString(Files.newInputStream(optional.get()), StandardCharsets.UTF_8);
                translations = new Gson().fromJson(content, new TypeToken<Map<String, String>>() {
                }.getType());
            } catch (Exception e) {
                Const.LOGGER.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    public static String get(String key, Object... args) {
        String translation1 = translations.get(key);
        if (translation1 != null) {
            return String.format(translation1, args);
        } else {
            String translation2 = Language.getInstance().getOrDefault(key);

            if (!translation2.equals(key)) {
                return String.format(translation2, args);
            } else {
                return "TranslateError{\"key\":\"" + key + "\",\"args\":" + Arrays.toString(args) + "}";
            }
        }
    }

    public static String get(String key) {
        return translations.get(key);
    }
}
