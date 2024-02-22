package com.chia.multienty.wechat.thirdparty.define.baseinfo;

/**
 * 微信认证信息
 */
public class WxVerifyInfo {
    /**
     * 是否资质认证，若是，拥有微信认证相关的权限。
     */
    private Boolean qualificationVerify;
    /**
     * 是否名称认证
     */
    private Boolean namingVerify;
    /**
     * 是否需要年审（qualification_verify == true 时才有该字段）
     */
    private Boolean annualReview;
    /**
     * 年审开始时间，时间戳（qualification_verify == true 时才有该字段）
     */
    private Boolean annualReviewBeginTime;
    /**
     * 年审截止时间，时间戳（qualification_verify == true 时才有该字段）
     */
    private Boolean annualReviewEndTime;
}
