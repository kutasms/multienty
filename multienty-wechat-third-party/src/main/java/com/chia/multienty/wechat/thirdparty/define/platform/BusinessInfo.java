package com.chia.multienty.wechat.thirdparty.define.platform;

/**
 * 用以了解功能的开通状况（0代表未开通，1代表已开通）
 */
public class BusinessInfo {
    /**
     * 是否开通微信支付功能
     */
    private Integer openPay;
    /**
     * 是否开通微信摇一摇功能
     */
    private Integer openShake;
    /**
     * 是否开通微信扫商品功能
     */
    private Integer openScan;
    /**
     * 是否开通微信卡券功能
     */
    private Integer openCard;
    /**
     * 是否开通微信门店功能
     */
    private Integer openStore;
}
