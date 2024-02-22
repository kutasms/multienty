package com.chia.multienty.core.sharding.tools;

public class DataSourceContext {
    private static ThreadLocal<String> dataSourceCache = new ThreadLocal<>();

    public static String getDataSourceName() {
        return dataSourceCache.get();
    }

    public static void setDataSourceName(String dataSourceName) {
        dataSourceCache.set(dataSourceName);
    }
}
