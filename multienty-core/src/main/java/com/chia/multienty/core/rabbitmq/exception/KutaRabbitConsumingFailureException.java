package com.chia.multienty.core.rabbitmq.exception;

public class KutaRabbitConsumingFailureException extends RuntimeException{
    public KutaRabbitConsumingFailureException(String message, Throwable cause) {
        super(message, cause);
    }
    public KutaRabbitConsumingFailureException(String message) {
        super(message);
    }
    public KutaRabbitConsumingFailureException(Throwable cause) {
        super(cause);
    }
    public KutaRabbitConsumingFailureException() {
        super();
    }
}
