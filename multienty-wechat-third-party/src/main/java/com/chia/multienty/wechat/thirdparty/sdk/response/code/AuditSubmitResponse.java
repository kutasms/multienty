package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 提交代码审核响应数据
 */
@Data
public class AuditSubmitResponse extends BaseResponse {
    /**
     * 审核编号
     */
    @JsonProperty(value = "auditid")
    private Long auditId;
}
