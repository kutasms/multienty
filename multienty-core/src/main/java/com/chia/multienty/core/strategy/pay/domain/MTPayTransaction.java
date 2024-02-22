package com.chia.multienty.core.strategy.pay.domain;

import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.util.TimeUtil;
import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.service.payments.model.PromotionDetail;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.payments.model.TransactionAmount;
import com.wechat.pay.java.service.payments.model.TransactionPayer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MTPayTransaction {

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
     * 设备号
     */
    private String deviceInfo;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 交易类型
     * <p>调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY</p>
     */
    private String tradeType;
    /**
     * 交易状态
     * <p>SUCCESS--支付成功<br/>
     * REFUND--转入退款<br/>
     * NOTPAY--未支付<br/>
     * CLOSED--已关闭<br/>
     * REVOKED--已撤销(刷卡支付)<br/>
     * USERPAYING--用户支付中<br/>
     * PAYERROR--支付失败(其他原因，如银行返回失败)<br/>
     * ACCEPT--已接收，等待扣款</p>
     */
    @SerializedName("trade_state")
    private String tradeState;

    /**
     * 付款银行
     */
    @SerializedName("bank_type")
    private String bankType;

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
     * 现金支付币种
     */
    private String cashFeeType;

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
     * 微信支付订单号
     */
    private String transactionId;

    /**
     * 商户订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 附加数据,透传
     */
    private String attach;

    /**
     * 支付完成时间
     */
    private String timeEnd;

    /**
     * 交易状态描述
     */
    @SerializedName("trade_state_desc")
    private String tradeStateDesc;

    /**
     * 获取完成时间
     * @return
     */
    public LocalDateTime getFinishTime() {
        if(timeEnd == null || timeEnd.length() != 14 && !getSuccess()) {
            return null;
        }
        return TimeUtil.s14ToDate(timeEnd);
    }

    @SerializedName("amount")
    private TransactionAmount amount;

    @SerializedName("mchid")
    private String mchid;

    @SerializedName("payer")
    private TransactionPayer payer;
    @SerializedName("promotion_detail")
    private List<PromotionDetail> promotionDetail;
    @SerializedName("success_time")
    private String successTime;

    private Transaction.TradeTypeEnum v3TradeType;


    /**
     * 交易是否成功
     * @return
     */
    public boolean getSuccess() {
        return StatusEnum.SUCCESS.name().equals(this.resultCode)
                && StatusEnum.SUCCESS.name().equals(this.returnCode)
                && StatusEnum.SUCCESS.name().equals(this.tradeState);
    }

}
