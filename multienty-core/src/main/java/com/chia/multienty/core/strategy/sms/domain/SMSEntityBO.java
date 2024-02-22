package com.chia.multienty.core.strategy.sms.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "SMSEntityBO",description = "短信实体BO")
public class SMSEntityBO {
    @ApiModelProperty("目标手机号")
    @JSONField(serialize = false)
    @JsonIgnore
    private List<String> targetPhoneNumbers;

}
