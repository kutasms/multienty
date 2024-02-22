package com.chia.multienty.core.rabbitmq;

public enum RabbitDeliveryStatus {
    WAITING,
    ERROR,
    SENT,
    EXCEPTION,
    PRODUCER_LOSE,
    CONSUMER_LOSE,
    CONSUMED,
    CONSUME_FAILURE,
    COMMITTED
}
