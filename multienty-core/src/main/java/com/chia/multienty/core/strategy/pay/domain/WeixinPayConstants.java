package com.chia.multienty.core.strategy.pay.domain;


public class WeixinPayConstants {
    /**
     * 业务执行结果
     * */
    public static final String RESULT_CODE = "result_code";
    /**
     * 返回结果
     */
    public static final String RETURN_CODE = "return_code";
    /**
     * 退款结果
     */
    public static final String REFUND_STATUS = "refund_status";
    /**
     * 微信支付商户号
     * */
    public static final String MCH_ID = "mch_id";
    /**
     * 随机字符结果
     * */
    public static final String NONCE_STR = "nonce_str";
    /**
     * 微信小程序appid
     * */
    public static final String APP_ID = "appid";
    /**
     * 签名
     * */
    public static final String SIGN = "sign";
    /**
     * 签名类型 MD5/HMAC-SHA256
     * */
    public static final String SIGN_TYPE = "sign_type";
    /**
     * 预支付编号
     * */
    public static final String PREPAY_ID = "prepay_id";
    /**
     * 支付用户的openid
     * */
    public static final String OPEN_ID = "openid";

    /**
     * 唯一支付订单号
     * */
    public static final String OUT_TRADE_NO = "out_trade_no";

    /**
     * 唯一退款订单号
     * */
    public static final String OUT_REFUND_NO = "out_refund_no";
    /**
     * 支付总额
     * */
    public static final String TOTAL_FEE = "total_fee";
    /**
     * 退款总额
     * */
    public static final String REFUND_FEE = "refund_fee";
    /**
     * 微信交易编号
     * */
    public static final String TRANSACTION_ID = "transaction_id";
    /**
     * 交易类型
     * */
    public static final String TRADE_TYPE = "trade_type";
    /**
     * 附加信息
     * */
    public static final String ATTACH = "attach";
    /**
     * 支付/退款信息
     * */
    public static final String BODY = "body";
    /**
     * 终端用户IP地址
     * */
    public static final String SPBILL_CREATE_IP = "spbill_create_ip";
    /**
     * 通知地址
     * */
    public static final String NOTIFY_URL = "notify_url";

    /**
     * 请求信息
     * */
    public static final String REQ_INFO = "req_info";
}
