package com.chia.multienty.core.tools;

import com.chia.multienty.core.domain.spi.SingletonSPI;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiTenantServiceLoader {
    private static final Map<Class<?>, Collection<Object>> clazzMap = new ConcurrentHashMap<>();

    public static void register(final Class<?> clazz) {
        if(!clazzMap.containsKey(clazz)) {
            clazzMap.put(clazz, load(clazz));
        }
    }

    private static <T> Collection<Object> load(final Class<T> clazz) {
        Collection<Object> result = new LinkedList<>();
        for(T item : ServiceLoader.load(clazz)) {
            result.add(item);
        }
        return result;
    }

    public static <T> Collection<T> getInstances(final Class<T> clazz) {
        return null == clazz.getAnnotation(SingletonSPI.class) ? createInstance(clazz) : getSingletonInstance(clazz);
    }
    @SneakyThrows(ReflectiveOperationException.class)
    private static <T> Collection<T> createInstance(final Class<T> clazz) {
        if(!clazzMap.containsKey(clazz)) {
            return Collections.emptyList();
        }
        Collection<Object> services = clazzMap.get(clazz);
        if(services.isEmpty()) {
            return Collections.emptyList();
        }
        Collection<T> list = new LinkedList<>();
        for(Object item: services) {
            list.add((T)item.getClass().getDeclaredConstructor().newInstance());
        }
        return list;
    }

    private static <T> Collection<T> getSingletonInstance(final Class<T> clazz) {
        return (Collection<T>) clazzMap.getOrDefault(clazz, Collections.emptyList());
    }

}
