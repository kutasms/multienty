package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.code.GrayReleasePlan;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取分阶段发布详情响应数据
 */
@Data
public class GrayReleasePlanGetResponse extends BaseResponse {
    /**
     * 分阶段发布计划详情
     */
    @JsonProperty("gray_release_plan")
    private GrayReleasePlan grayReleasePlan;
}
