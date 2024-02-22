package com.chia.multienty.core.strategy.pay.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.service.refund.model.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MTPayRefund {
    @SerializedName("refund_id")
    @JsonProperty("refund_id")
    @JSONField(name = "refund_id")
    private String refundId;
    @SerializedName("out_refund_no")
    @JsonProperty("out_refund_no")
    @JSONField(name = "out_refund_no")
    private String outRefundNo;
    @SerializedName("transaction_id")
    @JsonProperty("transaction_id")
    @JSONField(name = "transaction_id")
    private String transactionId;
    @SerializedName("out_trade_no")
    @JsonProperty("out_trade_no")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @SerializedName("user_received_account")
    @JsonProperty("user_received_account")
    @JSONField(name = "user_received_account")
    private String userReceivedAccount;
    @SerializedName("success_time")
    @JsonProperty("success_time")
    @JSONField(name = "success_time")
    private String successTime;
    @SerializedName("create_time")
    @JsonProperty("create_time")
    @JSONField(name = "create_time")
    private String createTime;
    @SerializedName("promotion_detail")
    @JsonProperty("promotion_detail")
    @JSONField(name = "promotion_detail")
    private List<Promotion> promotionDetail;
    @SerializedName("amount")
    @JsonProperty("amount")
    @JSONField(name = "amount")
    private Amount amount;
    @SerializedName("channel")
    @JsonProperty("channel")
    @JSONField(name = "channel")
    private Channel channel;
    @SerializedName("funds_account")
    @JsonProperty("funds_account")
    @JSONField(name = "funds_account")
    private FundsAccount fundsAccount;
    @SerializedName("status")
    @JsonProperty("status")
    @JSONField(name = "status")
    private Status status;

    /**
     * 返回状态码
     */
    private String returnCode;
    /**
     * 返回信息
     * <p>当return_code为FAIL时返回信息为错误原因 ，例如
     * 签名失败/参数格式校验错误</p>
     */
    private String returnMsg;

    /**
     * 公众账号ID,微信分配的公众账号ID
     */
    private String appid;
    /**
     * 商户号,微信支付分配的商户号
     */
    private String mchId;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 业务结果
     * <p>SUCCESS/FAIL</p>
     */
    private String resultCode;
    /**
     * 订单总退款次数
     * <p>订单总共已发生的部分退款次数，当请求参数传入offset后有返回</p>
     */
    private Integer totalRefundCount;
    /**
     * 错误代码
     * <p>当result_code为FAIL时返回错误代码，详细参见下文错误列表</p>
     */
    private String errCode;
    /**
     * 错误代码描述
     * <p>当result_code为FAIL时返回错误描述，详细参见下文错误列表</p>
     */
    private String errCodeDes;
    /**
     * 标价金额
     */
    private Integer totalFee;

    /**
     * 应结订单金额
     * <p>当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。</p>
     */
    private Integer settlementTotalFee;

    /**
     * 退款笔数
     */
    private Integer refundCount;


    /**
     * 标价币种
     * <p>货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY</p>
     */
    private String 	feeType;

    /**
     * 现金支付金额
     * <p>现金支付金额订单现金支付金额</p>
     */
    private Integer cashFee;


    /**
     * 代金券金额
     * <p>"代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额</p>
     */
    private Integer couponFee;

    /**
     * 代金券使用数量
     */
    private Integer couponCount;

    /**
     * 代金券退款总金额
     * <p>各退款单的代金券退款金额累加</p>
     */
    private String couponRefundFee;

    /**
     * 交易状态描述
     */
    private String tradeStateDesc;

    /**
     * 微信原始返回Map
     */
    private Map<String, Object> origMap;
}
