package com.chia.multienty.core.strategy.pay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 交易支付结果封装VO对象
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "WechatPrepayVO",description = "交易支付结果封装VO对象")
public class WechatPrepayVO {
    /**
     * appId
     */
    @ApiModelProperty(value = "appId")
    private String appId;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private String timeStamp;

    /**
     * 时间刻度数
     */
    @ApiModelProperty(value = "时间刻度数")
    private String nonceStr;

    /**
     * package
     */
    @ApiModelProperty(value = "package")
    @JsonProperty("package")
    private String packages;

    /**
     * 签名类型
     */
    @ApiModelProperty(value = "签名类型")
    private String signType;

    /**
     * 签名
     */
    @ApiModelProperty(value = "签名")
    private String sign;

    /**
     * 收款码url
     */
    @ApiModelProperty(value = "收款码url")
    private String paymentCodeUrl;

    /**
     * h5支付链接
     */
    @ApiModelProperty(value = "h5支付链接")
    private String h5PaymentUrl;

    /**
     * 商户号
     */
    @ApiModelProperty(value = "商户号")
    private String partnerId;

    /**
     * 交易号码
     */
    @ApiModelProperty(value = "交易号码")
    private String tradeNo;
}
