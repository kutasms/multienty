package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.AuditStatusGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 查询审核单状态
 */
@Data
@WxApi(url = MerchantApis.Code.GET_AUDIT_STATUS)
public class AuditStatusGetRequest extends AuthorizerBaseRequest<AuditStatusGetResponse> {
    /**
     * 提交审核时获得的审核 id
     */
    @JsonProperty(value = "auditid")
    private Long auditId;
}
