package cn.evole.mods.mcbot.api.connect;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.common.config.ModConfig;
import cn.evole.mods.mcbot.common.event.IBotEvent;
import cn.evole.onebot.client.OneBotClient;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/12 01:49
 * @Description:
 */
public class ConnectApi {
    public static void wsConnect() {
        if (Constants.onebot != null) Constants.onebot.close();//关闭线程
        Constants.onebot = null;//强制为null
        Constants.onebot = OneBotClient
                .create(ModConfig.get().getBotConfig().build())
                .open()
                .registerEvents(new IBotEvent());//重新实例化
        ModConfig.get().getStatus().getSEnable().setBooleanValue(true);
        ModConfig.get().getCommon().getEnable().setBooleanValue(true);
        Constants.connected = true;
    }


}
