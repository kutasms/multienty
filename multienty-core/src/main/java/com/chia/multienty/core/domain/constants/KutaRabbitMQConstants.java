package com.chia.multienty.core.domain.constants;

/**
 * RabbitMQ 常量定义
 * */
public class KutaRabbitMQConstants {
    /**
     * 数据保存队列
     */
    public static final String DATA_SAVE_NOTIFY_QUEUE = "kuta.queue.data.save.notify";
    /**
     * 管理人员通知队列
     * */
    public static final String USER_NOTIFY_QUEUE = "kuta.queue.user.notify";

    /**
     * 管理人员通知队列路由键
     * */
    public static final String USER_NOTIFY_ROUTING_KEY = "kuta.routing.key.user.notify";

    /**
     * 数据保存队列路由键
     */
    public static final String DATA_SAVE_ROUTING_KEY = "kuta.routing.key.data.save";
}
