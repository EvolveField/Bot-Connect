package cn.evole.mods.mcbot.api.event;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.Function;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/8/11 20:31
 * @Description: from <a href="https://github.com/AHilyard/Iceberg/blob/1.21-multi/common/src/main/java/com/anthonyhilyard/iceberg/events/TypeTrackedEvent.java">...</a>
 */
public class TypeTrackedEvent<S, T> extends Event<T> {
    private final Map<Class<? extends S>, T> listenerTypes = Maps.newHashMap();

    public TypeTrackedEvent(Class<? super T> type, Function<T[], T> invokerFactory) {
        super(type, invokerFactory);
    }

    @Override
    public void register(T listener) {
        throw new UnsupportedOperationException("Register(listener) unsupported.  Use Register(type, listener) instead!");
    }

    public void register(Class<? extends S> type, T listener) {
        super.register(listener);
        listenerTypes.put(type, listener);
    }

    public Map<Class<? extends S>, T> getListenerTypes() {
        return listenerTypes;
    }
}
