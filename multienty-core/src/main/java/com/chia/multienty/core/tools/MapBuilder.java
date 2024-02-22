package com.chia.multienty.core.tools;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K,V> {
    public static <K,V> MapBuilder<K, V> create() {
        return new MapBuilder<K,V>();
    }
    private Map<K,V> temp = new HashMap<>();

    public MapBuilder<K,V> add(K k, V v) {
        temp.put(k,v);
        return this;
    }
    public Map<K,V> get() {
        return this.temp;
    }
}
