package com.chia.multienty.wechat.thirdparty.define.baseinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 微信认证信息
 */
@Data
public class WxVerifyInfo {
    /**
     * 是否资质认证，若是，拥有微信认证相关的权限。
     */
    @JsonProperty("qualification_verify")
    private Boolean qualificationVerify;
    /**
     * 是否名称认证
     */
    @JsonProperty("naming_verify")
    private Boolean namingVerify;
    /**
     * 是否需要年审（qualification_verify == true 时才有该字段）
     */
    @JsonProperty("annual_review")
    private Boolean annualReview;
    /**
     * 年审开始时间，时间戳（qualification_verify == true 时才有该字段）
     */
    @JsonProperty("annual_review_begin_time")
    private Boolean annualReviewBeginTime;
    /**
     * 年审截止时间，时间戳（qualification_verify == true 时才有该字段）
     */
    @JsonProperty("annual_review_end_time")
    private Boolean annualReviewEndTime;
}
