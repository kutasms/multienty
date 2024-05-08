package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 查询服务商审核额度响应数据
 */
@Data
public class CodeAuditQuotaSetResponse extends BaseResponse {
    /**
     * quota剩余值
     */
    @JsonProperty("rest")
    private Integer rest;
    /**
     * 当月分配quota
     */
    @JsonProperty("limit")
    private Integer limit;
    /**
     * 剩余加急次数
     */
    @JsonProperty("speedup_rest")
    private Integer speedupRest;
    /**
     * 当月分配加急次数
     */
    @JsonProperty("speedup_limit")
    private Integer speedupLimit;
}
