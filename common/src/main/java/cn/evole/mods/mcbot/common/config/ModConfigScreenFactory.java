//package cn.evole.mods.mcbot.common.config;
//
//import com.mojang.serialization.Codec;
//import me.shedaniel.clothconfig2.api.ConfigBuilder;
//import me.shedaniel.clothconfig2.api.ConfigCategory;
//import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
//import me.shedaniel.clothconfig2.gui.entries.*;
//import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
//import net.minecraft.client.gui.screens.Screen;
//import net.minecraft.network.chat.Component;
//
//import java.util.function.Consumer;
//
///**
// * @Project: McBot
// * @Author: cnlimiter
// * @CreateTime: 2024/10/26 17:09
// * @Description:
// */
//public class ModConfigScreenFactory {
//    public static Screen createConfigScreen(Screen parent, ModConfig config, Consumer<ModConfig> update) {
//        ModConfig defaultConfig = ModConfig.DEFAULT;
//
//        ConfigBuilder builder = ConfigBuilder.create()
//                .setParentScreen(parent)
//                .setTitle(Component.translatable("title.mcbot.config"));
//
//
//        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
//
//        CommonConfig commonConfig = null;
//        CmdConfig cmdConfig = null;
//        StatusConfig statusConfig = null;
//        BotConfig botConfig = null;
//
//        {
//            BooleanListEntry groupOn = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCommon().isGroupOn())
//                    .setTooltip(Component.literal("开启q群功能"))
//                    .setDefaultValue(defaultConfig.getCommon().isGroupOn())
//                    .build();
//
//            LongListListEntry groupIdList = entryBuilder
//                    .startLongList(Component.translatable("option.mcbot.common.groupIdList"), config.getCommon().getGroupIdList())
//                    .setTooltip(Component.literal("支持多个q群"))
//                    .setDefaultValue(defaultConfig.getCommon().getGroupIdList())
//                    .build();
//
//            BooleanListEntry enabled = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCommon().isEnable())
//                    .setTooltip(Component.literal("是否启用"))
//                    .setDefaultValue(defaultConfig.getCommon().isEnable())
//                    .build();
//
//            BooleanListEntry debug = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCommon().isDebug())
//                    .setTooltip(Component.literal("是否开发模式，将显示事件信息操作"))
//                    .setDefaultValue(defaultConfig.getCommon().isDebug())
//                    .build();
//
//            BooleanListEntry autoOpen = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCommon().isAutoOpen())
//                    .setTooltip(Component.literal("自动连接"))
//                    .setDefaultValue(defaultConfig.getCommon().isAutoOpen())
//                    .build();
//
//            BooleanListEntry imageOn = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCommon().isImageOn())
//                    .setTooltip(Component.literal("是否开启聊天栏图片功能"))
//                    .setDefaultValue(defaultConfig.getCommon().isImageOn())
//                    .build();
//
//            BooleanListEntry bindOn = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCommon().isBindOn())
//                    .setTooltip(Component.literal("是否开启绑定校验"))
//                    .setDefaultValue(defaultConfig.getCommon().isBindOn())
//                    .build();
//
//            StringListEntry languageSelect = entryBuilder
//                    .startStrField(Component.translatable("option.mcbot.common.languageSelect"), config.getCommon().getLanguageSelect())
//                    .setTooltip(Component.literal("是否开启绑定校验"))
//                    .setDefaultValue(defaultConfig.getCommon().getLanguageSelect())
//                    .build();
//
//            ConfigCategory common = builder.getOrCreateCategory(Component.translatable("category.mcbot.common"));
//            common.addEntry(groupOn);
//            common.addEntry(groupIdList);
//            common.addEntry(enabled);
//            common.addEntry(debug);
//            common.addEntry(autoOpen);
//            common.addEntry(imageOn);
//            common.addEntry(bindOn);
//            common.addEntry(languageSelect);
//
//            commonConfig = new CommonConfig(
//                    groupOn.getValue(),
//                    groupIdList.getValue(),
//                    enabled.getValue(),
//                    debug.getValue(),
//                    languageSelect.getValue(),
//                    autoOpen.getValue(),
//                    imageOn.getValue(),
//                    bindOn.getValue()
//            );
//        }
//
//        {
//            BooleanListEntry connectInfoEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isConnectInfoEnable())
//                    .setTooltip(Component.literal("连接提醒"))
//                    .setDefaultValue(defaultConfig.getStatus().isConnectInfoEnable())
//                    .build();
//
//            BooleanListEntry rEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isREnable())
//                    .setTooltip(Component.literal("全局接收"))
//                    .setDefaultValue(defaultConfig.getStatus().isREnable())
//                    .build();
//
//            BooleanListEntry rCmdEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isRCmdEnable())
//                    .setTooltip(Component.literal("命令接收"))
//                    .setDefaultValue(defaultConfig.getStatus().isRCmdEnable())
//                    .build();
//
//            BooleanListEntry rChatEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isRChatEnable())
//                    .setTooltip(Component.literal("消息接收"))
//                    .setDefaultValue(defaultConfig.getStatus().isRChatEnable())
//                    .build();
//
//
//            BooleanListEntry sEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isSEnable())
//                    .setTooltip(Component.literal("发送消息"))
//                    .setDefaultValue(defaultConfig.getStatus().isSEnable())
//                    .build();
//
//            BooleanListEntry sQqWelcomeEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isSQqWelcomeEnable())
//                    .setTooltip(Component.literal("发送欢迎玩家入群消息"))
//                    .setDefaultValue(defaultConfig.getStatus().isSQqWelcomeEnable())
//                    .build();
//
//            BooleanListEntry sQqLeaveEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isSQqLeaveEnable())
//                    .setTooltip(Component.literal("发送玩家退群消息"))
//                    .setDefaultValue(defaultConfig.getStatus().isSQqLeaveEnable())
//                    .build();
//
//            BooleanListEntry sJoinEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isSJoinEnable())
//                    .setTooltip(Component.literal("发送加入服务器消息"))
//                    .setDefaultValue(defaultConfig.getStatus().isSJoinEnable())
//                    .build();
//
//            BooleanListEntry sLeaveEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isSLeaveEnable())
//                    .setTooltip(Component.literal("发送离开服务器消息"))
//                    .setDefaultValue(defaultConfig.getStatus().isSLeaveEnable())
//                    .build();
//
//            BooleanListEntry sDeathEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isSDeathEnable())
//                    .setTooltip(Component.literal("发送玩家死亡消息"))
//                    .setDefaultValue(defaultConfig.getStatus().isSDeathEnable())
//                    .build();
//
//            BooleanListEntry sChatEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isSChatEnable())
//                    .setTooltip(Component.literal("发送服务器聊天"))
//                    .setDefaultValue(defaultConfig.getStatus().isSChatEnable())
//                    .build();
//
//            BooleanListEntry sAdvanceEnable = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getStatus().isSAdvanceEnable())
//                    .setTooltip(Component.literal("发送成就消息"))
//                    .setDefaultValue(defaultConfig.getStatus().isSAdvanceEnable())
//                    .build();
//
//            ConfigCategory status = builder.getOrCreateCategory(Component.translatable("category.mcbot.status"));
//            status.addEntry(rEnable);
//            status.addEntry(rCmdEnable);
//            status.addEntry(rChatEnable);
//            status.addEntry(sEnable);
//            status.addEntry(sQqWelcomeEnable);
//            status.addEntry(sQqLeaveEnable);
//            status.addEntry(sJoinEnable);
//            status.addEntry(sLeaveEnable);
//            status.addEntry(sDeathEnable);
//            status.addEntry(sChatEnable);
//            status.addEntry(sAdvanceEnable);
//            status.addEntry(connectInfoEnable);
//
//            statusConfig = new StatusConfig(
//                    connectInfoEnable.getValue(),
//                    rEnable.getValue(),
//                    rCmdEnable.getValue(),
//                    rChatEnable.getValue(),
//                    sEnable.getValue(),
//                    sQqWelcomeEnable.getValue(),
//                    sQqLeaveEnable.getValue(),
//                    sJoinEnable.getValue(),
//                    sLeaveEnable.getValue(),
//                    sDeathEnable.getValue(),
//                    sChatEnable.getValue(),
//                    sAdvanceEnable.getValue()
//            );
//        }
//
//        {
//            StringListEntry welcomeNotice = entryBuilder
//                    .startTextField(Component.translatable("option.mcbot.welcomeNotice"), config.getCmd().getWelcomeNotice())
//                    .setTooltip(Component.literal("q群中使用命令的关键符号"))
//                    .setDefaultValue(defaultConfig.getCmd().getWelcomeNotice())
//                    .setSaveConsumer(s -> config.getCmd().setWelcomeNotice(s))
//                    .build();
//            StringListEntry leaveNotice = entryBuilder
//                    .startTextField(Component.translatable("option.mcbot.leaveNotice"), config.getCmd().getLeaveNotice())
//                    .setTooltip(Component.literal("q群中使用命令的关键符号"))
//                    .setDefaultValue(defaultConfig.getCmd().getLeaveNotice())
//                    .setSaveConsumer(s -> config.getCmd().setLeaveNotice(s))
//                    .build();
//            StringListEntry cmdStart = entryBuilder
//                    .startTextField(Component.translatable("option.mcbot.cmd_start"), config.getCmd().getCmdStart())
//                    .setTooltip(Component.literal("q群中使用命令的关键符号"))
//                    .setDefaultValue(defaultConfig.getCmd().getCmdStart())
//                    .setSaveConsumer(s -> config.getCmd().setCmdStart(s))
//                    .build();
//            StringListEntry mcPrefix = entryBuilder
//                    .startTextField(Component.translatable("option.mcbot.mc_prefix"), config.getCmd().getMcPrefix())
//                    .setTooltip(Component.literal("来自游戏的消息显示到群中的前缀"))
//                    .setDefaultValue(defaultConfig.getCmd().getMcPrefix())
//                    .setSaveConsumer(s -> config.getCmd().setMcPrefix(s))
//                    .build();
//            StringListEntry qqGamePrefix = entryBuilder
//                    .startTextField(Component.translatable("option.mcbot.qq_prefix"), config.getCmd().getQqGamePrefix())
//                    .setTooltip(Component.literal("来自q群的消息显示到游戏中的前缀"))
//                    .setDefaultValue(defaultConfig.getCmd().getQqGamePrefix())
//                    .setSaveConsumer(s -> config.getCmd().setQqGamePrefix(s))
//                    .build();
//            BooleanListEntry mcPrefixOn = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCmd().isMcPrefixOn())
//                    .setTooltip(Component.literal("是否开启游戏中自定义关键词"))
//                    .setDefaultValue(defaultConfig.getCmd().isMcPrefixOn())
//                    .build();
//            BooleanListEntry gamePrefixOn = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCmd().isGamePrefixOn())
//                    .setTooltip(Component.literal("是否开启显示到游戏中的前缀"))
//                    .setDefaultValue(defaultConfig.getCmd().isGamePrefixOn())
//                    .build();
//            BooleanListEntry idGamePrefixOn = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCmd().isIdGamePrefixOn())
//                    .setTooltip(Component.literal("是否开启显示到游戏中的id前缀"))
//                    .setDefaultValue(defaultConfig.getCmd().isIdGamePrefixOn())
//                    .build();
//            BooleanListEntry groupNickOn = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCmd().isGroupNickOn())
//                    .setTooltip(Component.literal("是否开启游戏中自定义关键词"))
//                    .setDefaultValue(defaultConfig.getCmd().isGroupNickOn())
//                    .build();
//            BooleanListEntry qqChatPrefixOn = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCmd().isQqChatPrefixOn())
//                    .setTooltip(Component.literal("是否开启qq中自定义关键词"))
//                    .setDefaultValue(defaultConfig.getCmd().isQqChatPrefixOn())
//                    .build();
//            BooleanListEntry mcChatPrefixOn = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getCmd().isMcChatPrefixOn())
//                    .setTooltip(Component.literal("是否开启游戏中自定义关键词"))
//                    .setDefaultValue(defaultConfig.getCmd().isMcChatPrefixOn())
//                    .build();
//            StringListEntry mcChatPrefix = entryBuilder
//                    .startTextField(Component.translatable("option.mcbot.mc_chat_prefix"), config.getCmd().getMcChatPrefix())
//                    .setTooltip(Component.literal("游戏中自定义的消息头文本"))
//                    .setDefaultValue(defaultConfig.getCmd().getMcChatPrefix())
//                    .setSaveConsumer(s -> config.getCmd().setMcChatPrefix(s))
//                    .build();
//            StringListEntry qqChatPrefix = entryBuilder
//                    .startTextField(Component.translatable("option.mcbot.qq_chat_prefix"), config.getCmd().getQqChatPrefix())
//                    .setTooltip(Component.literal("qq中自定义的消息头文本"))
//                    .setDefaultValue(defaultConfig.getCmd().getQqChatPrefix())
//                    .setSaveConsumer(s -> config.getCmd().setQqChatPrefix(s))
//                    .build();
//            ConfigCategory cmd = builder.getOrCreateCategory(Component.translatable("category.mcbot.cmd"));
//            cmd.addEntry(welcomeNotice);
//            cmd.addEntry(leaveNotice);
//            cmd.addEntry(cmdStart);
//            cmd.addEntry(gamePrefixOn);
//            cmd.addEntry(idGamePrefixOn);
//            cmd.addEntry(qqGamePrefix);
//            cmd.addEntry(groupNickOn);
//            cmd.addEntry(mcPrefixOn);
//            cmd.addEntry(mcPrefix);
//            cmd.addEntry(mcChatPrefixOn);
//            cmd.addEntry(qqChatPrefixOn);
//            cmd.addEntry(mcChatPrefix);
//            cmd.addEntry(qqChatPrefix);
//
//            cmdConfig = new CmdConfig(
//                    welcomeNotice.getValue(),
//                    leaveNotice.getValue(),
//                    cmdStart.getValue(),
//                    gamePrefixOn.getValue(),
//                    idGamePrefixOn.getValue(),
//                    qqGamePrefix.getValue(),
//                    "频道",
//                    groupNickOn.getValue(),
//                    mcPrefixOn.getValue(),
//                    mcPrefix.getValue(),
//                    mcChatPrefixOn.getValue(),
//                    qqChatPrefixOn.getValue(),
//                    mcChatPrefix.getValue(),
//                    qqChatPrefix.getValue()
//            );
//        }
//
//        {
//            //将BotConfig按照上面的格式添加到builder中
//            StringListEntry tag = entryBuilder
//                    .startTextField(Component.translatable("option.mcbot.tag"), config.getBotConfig().getTag())
//                    .setTooltip(Component.literal("跨服支持,权限支持"))
//                    .setDefaultValue(defaultConfig.getBotConfig().getTag())
//                    .setSaveConsumer(s -> config.getBotConfig().setTag(s))
//                    .build();
//            StringListEntry url = entryBuilder
//                    .startTextField(Component.translatable("option.mcbot.url"), config.getBotConfig().getUrl())
//                    .setTooltip(Component.literal("地址（支持域名和ipv6）"))
//                    .setDefaultValue(defaultConfig.getBotConfig().getUrl())
//                    .setSaveConsumer(s -> config.getBotConfig().setUrl(s))
//                    .build();
//            StringListEntry token = entryBuilder
//                    .startTextField(Component.translatable("option.mcbot.token"), config.getBotConfig().getToken())
//                    .setTooltip(Component.literal("鉴权"))
//                    .setDefaultValue(defaultConfig.getBotConfig().getToken())
//                    .setSaveConsumer(s -> config.getBotConfig().setToken(s))
//                    .build();
//            LongListEntry botId = entryBuilder
//                    .startLongField(Component.translatable("option.mcbot.bot_id"), config.getBotConfig().getBotId())
//                    .setTooltip(Component.literal("机器人qq"))
//                    .setDefaultValue(defaultConfig.getBotConfig().getBotId())
//                    .setSaveConsumer(s -> config.getBotConfig().setBotId(s))
//                    .build();
//            BooleanListEntry reconnect = entryBuilder
//                    .startBooleanToggle(Component.translatable("option.mcbot.enabled"), config.getBotConfig().isReconnect())
//                    .setTooltip(Component.literal("自动重连"))
//                    .setDefaultValue(defaultConfig.getBotConfig().isReconnect())
//                    .build();
//            IntegerListEntry maxReconnectAttempts = entryBuilder
//                    .startIntField(Component.translatable("option.mcbot.max_reconnect_attempts"), config.getBotConfig().getMaxReconnectAttempts())
//                    .setTooltip(Component.literal("自动重连次数"))
//                    .setMin(1)
//                    .setDefaultValue(defaultConfig.getBotConfig().getMaxReconnectAttempts())
//                    .setSaveConsumer(s -> config.getBotConfig().setMaxReconnectAttempts(s))
//                    .build();
//            LongListEntry timeoutCompensation = entryBuilder
//                    .startLongField(Component.translatable("option.mcbot.timeout_compensation"), config.getBotConfig().getTimeoutCompensation())
//                    .setTooltip(Component.literal("超时宽容度（毫秒）"))
//                    .setMin(0)
//                    .setDefaultValue(defaultConfig.getBotConfig().getTimeoutCompensation())
//                    .setSaveConsumer(s -> config.getBotConfig().setTimeoutCompensation(s))
//                    .build();
//            ConfigCategory bot = builder.getOrCreateCategory(Component.translatable("category.mcbot.bot"));
//            bot.addEntry(tag);
//            bot.addEntry(url);
//            bot.addEntry(token);
//            bot.addEntry(botId);
//            bot.addEntry(reconnect);
//            bot.addEntry(maxReconnectAttempts);
//            bot.addEntry(timeoutCompensation);
//
//            botConfig = new BotConfig(
//                    tag.getValue(),
//                    url.getValue(),
//                    token.getValue(),
//                    botId.getValue(),
//                    reconnect.getValue(),
//                    maxReconnectAttempts.getValue(),
//                    timeoutCompensation.getValue()
//            );
//
//        }
//
//        CommonConfig finalCommonConfig = commonConfig;
//        StatusConfig finalStatusConfig = statusConfig;
//        CmdConfig finalCmdConfig = cmdConfig;
//        BotConfig finalBotConfig = botConfig;
//        builder.setSavingRunnable(() -> update.accept(new ModConfig(
//                finalCommonConfig,
//                finalStatusConfig,
//                finalCmdConfig,
//                finalBotConfig
//        )));
//
//        return builder.build();
//    }
//}
