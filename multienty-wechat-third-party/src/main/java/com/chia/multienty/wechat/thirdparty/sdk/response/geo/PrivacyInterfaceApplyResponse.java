package com.chia.multienty.wechat.thirdparty.sdk.response.geo;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 申请地理位置接口响应数据
 */
@Data
public class PrivacyInterfaceApplyResponse extends BaseResponse {
    /**
     * 审核id
     */
    private Long auditId;
}
