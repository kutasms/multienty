package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.CodeAuditSpeedupResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 加急代码审核
 */
@Data
@WxApi(url = MerchantApis.Code.SPEED_UP_CODE_AUDIT)
public class CodeAuditSpeedupRequest extends ComponentBaseRequest<CodeAuditSpeedupResponse> {
    /**
     * 审核单ID
     */
    @JsonProperty(value = "auditid")
    private Long auditId;
}
