package com.chia.multienty.core.domain.basic;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class BigDecimalWrapper {
    private BigDecimal value;
}
