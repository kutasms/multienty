package com.chia.multienty.core.domain.wechat;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信小程序模版消息类型
 */
@Getter
@AllArgsConstructor
public enum WxMppTemplateType {
    TRADE_PAY_REMIND(1, "支付提醒"),
    TRADE_PAID(2,"交易已完成"),
    REFUND_SUCCESS(3, "退款成功"),
    TRADE_CLOSED(4, "交易已关闭");
    private Integer value;
    private String describe;
}
