package com.chia.multienty.core.strategy.pay.domain.request;

import com.chia.multienty.core.strategy.pay.domain.PayPurpose;
import com.chia.multienty.core.strategy.pay.domain.PaySource;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MTPrepayRequest {
    private Long programId;
    private String tradeNo;
    private BigDecimal money;
    private  String openid;
    private String ip;
    private PaySource source;
    private PayPurpose purpose;
    private Long tenantId;
}
