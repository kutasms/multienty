package com.chia.multienty.wechat.scheduling.rabbitmq.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WechatBoType {
    REFRESH_ACCESS_TOKEN(1, "刷新授权令牌");
    private Integer value;
    private String describe;
}
