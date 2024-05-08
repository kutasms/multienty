package com.chia.multienty.core.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RabbitPushResult implements Serializable {
    private Boolean success;
    private Throwable throwable;
    private String key;

    private String errorMsg;

    private Integer errorCode;
}
