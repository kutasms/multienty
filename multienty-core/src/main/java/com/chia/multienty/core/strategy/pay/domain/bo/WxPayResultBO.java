package com.chia.multienty.core.strategy.pay.domain.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 微信支付通知结果
 * @see <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7&index=8">...</a>
 * */
@Data
@Accessors(chain = true)
@ApiModel(value = "WxPayResultBO",description = "微信支付结果")
public class WxPayResultBO {
    @ApiModelProperty("返回状态码")
    private String returnCode;
    @ApiModelProperty("返回信息")
    private String returnMsg;
    @JSONField(name = "appid")
    @JsonProperty(value = "appid")
    @ApiModelProperty("小程序ID")
    private String appId;
    @ApiModelProperty("商户号")
    private String mchId;
    @ApiModelProperty("设备号")
    private String deviceInfo;
    @ApiModelProperty("随机字符串")
    private String nonceStr;
    @ApiModelProperty("签名")
    private String sign;
    @ApiModelProperty("签名类型")
    private String signType;
    @ApiModelProperty("业务结果")
    private String resultCode;
    @ApiModelProperty("错误代码")
    private String errCode;
    @ApiModelProperty("错误代码描述")
    private String errCodeDes;
    @JSONField(name = "openid")
    @JsonProperty(value = "openid")
    @ApiModelProperty("用户标识")
    private String openId;
    @ApiModelProperty("用户是否关注公众账号，Y-关注，N-未关注")
    private String isSubscribe;
    @ApiModelProperty("交易类型 JSAPI、NATIVE、APP")
    private String tradeType;
    /**
     * 付款银行
     * @see https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     * */
    @ApiModelProperty("付款银行")
    private String bankType;
    @ApiModelProperty("订单总金额，单位为分")
    private Integer totalFee;
    @ApiModelProperty("应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。")
    private Integer settlementTotalFee;
    /**
     * 货币种类
     * @see https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     * */
    @ApiModelProperty("货币种类")
    private String feeType;
    @ApiModelProperty("现金支付金额")
    private Integer cashFee;
    @ApiModelProperty("微信支付订单号")
    private String transactionId;
    @ApiModelProperty("商户订单号")
    private String outTradeNo;
    @ApiModelProperty("商家数据包，原样返回")
    private String attach;
    @ApiModelProperty("支付完成时间")
    private String timeEnd;
    @ApiModelProperty("租户编号")
    private Long tenantId;
    @ApiModelProperty("程序编号")
    private Long programId;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
