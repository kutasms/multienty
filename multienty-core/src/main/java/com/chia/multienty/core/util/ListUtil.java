package com.chia.multienty.core.util;

import java.util.Collection;

public class ListUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }
}
