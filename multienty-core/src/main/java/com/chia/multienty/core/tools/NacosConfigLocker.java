package com.chia.multienty.core.tools;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class NacosConfigLocker {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * 确保无nacos更新操作
     * @return
     */
    public static boolean ensureNoNacosUpgrade() {
        /**
         * 只有当他等于0时，表示nacos配置更新逻辑完成
         * 在nacos配置更新事件处理逻辑中首先atomicInteger.add
         * 然后再执行更新逻辑
         */
        return atomicInteger.get() == 0;

    }


}
