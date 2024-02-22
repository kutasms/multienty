
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.chia.multienty.core.cache.redis.listener;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Properties;

public abstract class KeyspaceEventMessageListener implements MessageListener, InitializingBean, DisposableBean {
    private static final Topic TOPIC_ALL_KEYEVENTS = new PatternTopic("__keyevent@*");
    private final RedisMessageListenerContainer listenerContainer;
    private String keyspaceNotificationsConfigParameter = "EA";

    public KeyspaceEventMessageListener(RedisMessageListenerContainer listenerContainer) {
        Assert.notNull(listenerContainer, "RedisMessageListenerContainer to run in must not be null!");
        this.listenerContainer = listenerContainer;
    }

    public void onMessage(Message message, @Nullable byte[] pattern) {
        if (message != null && !ObjectUtils.isEmpty(message.getChannel()) && !ObjectUtils.isEmpty(message.getBody())) {
            this.doHandleMessage(message);
        }
    }

    protected abstract void doHandleMessage(Message var1);

    public void init() {
        if (StringUtils.hasText(this.keyspaceNotificationsConfigParameter)) {
            RedisConnection connection = this.listenerContainer.getConnectionFactory().getConnection();

            try {
                Properties config = connection.getConfig("notify-keyspace-events");
                if (!StringUtils.hasText(config.getProperty("notify-keyspace-events"))) {
                    connection.setConfig("notify-keyspace-events", this.keyspaceNotificationsConfigParameter);
                }
            } finally {
                connection.close();
            }
        }

//        this.doRegister(this.listenerContainer);
    }

    protected void doRegister(RedisMessageListenerContainer container) {
        this.listenerContainer.addMessageListener(this, TOPIC_ALL_KEYEVENTS);
    }

    public void destroy() throws Exception {
        this.listenerContainer.removeMessageListener(this);
    }

    public void setKeyspaceNotificationsConfigParameter(String keyspaceNotificationsConfigParameter) {
        this.keyspaceNotificationsConfigParameter = keyspaceNotificationsConfigParameter;
    }

    public void afterPropertiesSet() throws Exception {
        this.init();
    }
}
