package com.chia.multienty.core.strategy.sms.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "SMSSendingResult", description="短信发送结果")
public class SMSResult {
    @ApiModelProperty("原始结果")
    private String origResult;
    @ApiModelProperty("数据")
    private Object data;
}
