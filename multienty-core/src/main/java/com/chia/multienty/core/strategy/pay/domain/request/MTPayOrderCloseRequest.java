package com.chia.multienty.core.strategy.pay.domain.request;

import lombok.Data;

@Data
public class MTPayOrderCloseRequest {
    private Long programId;
    private String outTradeNo;
}
