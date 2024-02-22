package com.chia.multienty.core.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RabbitPushResult {
    private Boolean success;
    private Throwable throwable;
    private String key;

    private String errorMsg;

    private Integer errorCode;
}
