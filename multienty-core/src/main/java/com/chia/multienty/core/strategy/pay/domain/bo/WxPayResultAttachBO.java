package com.chia.multienty.core.strategy.pay.domain.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.chia.multienty.core.strategy.pay.domain.PayPurpose;
import com.chia.multienty.core.strategy.pay.domain.PaySource;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "WxPayResultAttachBO",description = "微信支付结果-商家数据包对象")
public class WxPayResultAttachBO {
    @ApiModelProperty("来源")
    private PaySource source;
    @ApiModelProperty("目的")
    private PayPurpose purpose;
    @ApiModelProperty("租户编号")
    @JsonProperty("tenant_id")
    @JSONField(name = "tenant_id")
    private Long tenantId;
}
