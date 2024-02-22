package com.chia.multienty.core.tools;

import com.chia.multienty.core.util.SpringUtil;


public class IdWorkerProvider {
    private static SnowflakeIdWorker idWorker;
    public static Long next() {
        if(idWorker == null) {
            idWorker = SpringUtil.getBean(SnowflakeIdWorker.class);
        }
        return idWorker.nextId();
    }

    public static String nextStr() {;
        return next().toString();
    }
}
