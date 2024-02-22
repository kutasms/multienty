package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 查询服务商审核额度响应数据
 */
@Data
public class CodeAuditQuotaSetResponse extends BaseResponse {
    /**
     * quota剩余值
     */
    private Integer rest;
    /**
     * 当月分配quota
     */
    private Integer limit;
    /**
     * 剩余加急次数
     */
    private Integer speedupRest;
    /**
     * 当月分配加急次数
     */
    private Integer speedupLimit;
}
