package com.chia.multienty.core.strategy.pay.domain.request;

import com.chia.multienty.core.domain.enums.TerminalType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class MTRefundRequest {
    private Long programId;
    private String transactionId;
    private String outRefundNo;
    private BigDecimal total;
    private BigDecimal refund;
    private String reason;
    private TerminalType terminalType;
}
