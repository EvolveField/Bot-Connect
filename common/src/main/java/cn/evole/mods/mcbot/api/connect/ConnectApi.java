package cn.evole.mods.mcbot.api.connect;

import cn.evole.mods.mcbot.Constants;
import cn.evole.mods.mcbot.core.event.IBotEvent;
import cn.evole.onebot.client.OneBotClient;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/12 01:49
 * @Description:
 */
public class ConnectApi {
    public static void wsConnect(){
        Constants.onebot.close();//关闭线程
        Constants.onebot = null;//强制为null
        Constants.onebot = OneBotClient
                .create(Constants.configManager.config().getBotConfig().build())
                .open()
                .registerEvents(new IBotEvent());//重新实例化
        Constants.configManager.config().getStatus().setREnable(true);
        Constants.configManager.config().getCommon().setEnable(true);
        Constants.connected = true;
    }


}
