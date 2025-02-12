<p align="center">
    <img width="300" src="https://s2.loli.net/2022/10/02/zrebhtAKjc3GyIl.png" alt="title">  
</p>
<div align="center">

# McBot

_✨ Based on [OneBot](https://github.com/howmanybots/onebot/blob/master/README.md) protocol's MineCraft QQ Robot✨_

</div>
<hr>
<p align="center">
    <a href="https://github.com/Nova-Committee/McBot/issues"><img src="https://img.shields.io/github/issues/Nova-Committee/Bot-Connect?style=flat" alt="issues" /></a>
    <a href="https://www.curseforge.com/minecraft/mc-mods/botconnect">
        <img src="http://cf.way2muchnoise.eu/botconnect.svg" alt="CurseForge Download">
    </a>
    <img src="https://img.shields.io/badge/license-GPLV3-green" alt="License">
    <a href="https://github.com/howmanybots/onebot"><img src="https://img.shields.io/badge/OneBot-v11-blue?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABABAMAAABYR2ztAAAAIVBMVEUAAAAAAAADAwMHBwceHh4UFBQNDQ0ZGRkoKCgvLy8iIiLWSdWYAAAAAXRSTlMAQObYZgAAAQVJREFUSMftlM0RgjAQhV+0ATYK6i1Xb+iMd0qgBEqgBEuwBOxU2QDKsjvojQPvkJ/ZL5sXkgWrFirK4MibYUdE3OR2nEpuKz1/q8CdNxNQgthZCXYVLjyoDQftaKuniHHWRnPh2GCUetR2/9HsMAXyUT4/3UHwtQT2AggSCGKeSAsFnxBIOuAggdh3AKTL7pDuCyABcMb0aQP7aM4AnAbc/wHwA5D2wDHTTe56gIIOUA/4YYV2e1sg713PXdZJAuncdZMAGkAukU9OAn40O849+0ornPwT93rphWF0mgAbauUrEOthlX8Zu7P5A6kZyKCJy75hhw1Mgr9RAUvX7A3csGqZegEdniCx30c3agAAAABJRU5ErkJggg=="></a>

</p>

<p align="center">
    <a href="README.md">简体中文</a> |
    <a href="https://github.com/Nova-Committee/McBot/blob/all/README_EN.md#lts">LTS</a> |
    <a href="https://github.com/Nova-Committee/McBot/blob/all/README_EN.md#quickstart">QuickStart</a>
</p>

# LTS

> Forge-all
> Fabric-all

# QuickStart

### Use api to request

```java
public class TestCmd {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("test")
                .executes(TestCmd::execute);
    }

    public static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        System.out.println(McBot.bot.sendGroupMsg(337631140, MsgUtils.builder().text("1").build(), true));
        //send msg to group
        return 0;
    }
}
```

### Event Listener Instance

```java
public class WebSocketServerTest {
    public static void main(String[] args) throws Exception {
        public static LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();//use queue
        public static Thread app = new Thread(() -> {
            service = new ConnectFactory(new BotConfig(), blockingQueue);//create websocket session
            bot = service.ws.createBot();//create bot instance
        }, "BotServer");
        app.start();
        EventBus bus = new EventBus(blockingQueue);//create bus
        GroupMessageListener groupMessageListener = new GroupMessageListener();
        groupMessageListener.addHandler("天气", new Handler<GroupMessageEvent>() {
            @Override
            public void handle(GroupMessageEvent groupMessage) {
                System.out.println(groupMessage);

            }
        });
        bus.addListener(groupMessageListener);//add listener
        bus.addListener(new SimpleListener<PrivateMessageEvent>() {//private chat listener
            @Override
            public void onMessage(PrivateMessageEvent privateMessage) {
                System.out.println(privateMessage);
            }
        });

    }
}
```

# Client

McBot  [OneBot-v11](https://github.com/howmanybots/onebot/tree/master/v11/specs)
developed with standard protocols, compatible with all One Bot protocol clients that support forward Web sockets

| Project Address                                                                | Platform                                      | Authors        | Note                                                                      |
|--------------------------------------------------------------------------------|-----------------------------------------------|----------------|---------------------------------------------------------------------------|
| [koishijs/koishi](https://github.com/koishijs/koishi)                          | [koishi](https://koishi.js.org/)              | shigma         |                                                                           |
| [onebot-walle/walle-q](https://github.com/onebot-walle/walle-q)                |                                               | abrahum        |                                                                           |
| [Yiwen-Chan/OneBot-YaYa](https://github.com/Yiwen-Chan/OneBot-YaYa)            | [先驱](https://www.xianqubot.com/)              | kanri          |                                                                           |
| [richardchien/coolq-http-api](https://github.com/richardchien/coolq-http-api)  | CKYU                                          | richardchien   | Can be used by [mirai-native](https://github.com/iTXTech/mirai-native)    |
| [Mrs4s/go-cqhttp](https://github.com/Mrs4s/go-cqhttp)                          | [MiraiGo](https://github.com/Mrs4s/MiraiGo)   | Mrs4s          |                                                                           |
| [yyuueexxiinngg/OneBot-Mirai](https://github.com/yyuueexxiinngg/onebot-kotlin) | [Mirai](https://github.com/mamoe/mirai)       | yyuueexxiinngg |                                                                           |
| [takayama-lily/onebot](https://github.com/takayama-lily/onebot)                | [OICQ](https://github.com/takayama-lily/oicq) | takayama       |                                                                           |

# Credits

* [OneBot](https://github.com/botuniverse/onebot)

# License

This product is licensed under the GNU General Public License version 3. The license is as published by the Free
Software Foundation published at https://www.gnu.org/licenses/gpl-3.0.html.

Alternatively, this product is licensed under the GNU Lesser General Public License version 3 for non-commercial use.
The license is as published by the Free Software Foundation published at https://www.gnu.org/licenses/lgpl-3.0.html.

Feel free to contact us if you have any questions about licensing or want to use the library in a commercial closed
source product.

# Thanks

Thanks to [JetBrains](https://www.jetbrains.com/?from=mcbot) for allocating free open-source licences for IDEs such as [IntelliJ IDEA](https://www.jetbrains.com/idea/?from=mcbot)
[<img src=".github/jetbrains-variant-3.png" width="200"/>](https://www.jetbrains.com/?from=mcbot)  

## Stargazers over time

[![Stargazers over time](https://starchart.cc/Nova-Committee/McBot.svg)](https://starchart.cc/Nova-Committee/McBot)


