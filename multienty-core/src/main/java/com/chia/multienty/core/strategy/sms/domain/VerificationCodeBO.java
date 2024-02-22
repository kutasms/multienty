package com.chia.multienty.core.strategy.sms.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "VerificationCodeBO",description = "验证码BO对象")
public class VerificationCodeBO extends SMSEntityBO {
    @ApiModelProperty("验证码")
    private String code;
}
