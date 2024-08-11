package cn.evole.mods.mcbot.api.event;

import com.google.common.collect.MapMaker;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/11 20:31
 * @Description: from <a href="https://github.com/AHilyard/Iceberg/blob/1.21-multi/common/src/main/java/com/anthonyhilyard/iceberg/events/EventFactory.java">...</a>
 */
public final class EventFactory
{
    private static final Set<Event<?>> EVENTS = Collections.newSetFromMap(new MapMaker().weakKeys().makeMap());

    private EventFactory() { }

    public static void invalidate()
    {
        EVENTS.forEach(Event::update);
    }

    public static <T> Event<T> create(Class<? super T> type, Function<T[], T> invokerFactory)
    {
        Event<T> event = new Event<>(type, invokerFactory);
        EVENTS.add(event);
        return event;
    }

    public static <S, T> TypeTrackedEvent<S, T> createTypeTracked(Class<? super T> type, Function<T[], T> invokerFactory)
    {
        TypeTrackedEvent<S, T> event = new TypeTrackedEvent<>(type, invokerFactory);
        EVENTS.add(event);
        return event;
    }
}
