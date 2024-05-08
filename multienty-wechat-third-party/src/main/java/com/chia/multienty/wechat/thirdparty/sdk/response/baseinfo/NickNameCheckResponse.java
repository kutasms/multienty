package com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序名称检测响应数据
 */
@Data
public class NickNameCheckResponse extends BaseResponse {
    /**
     * 是否命中关键字策略。若命中，可以选填关键字材料
     */
    @JsonProperty("hit_condition")
    private Boolean hitCondition;
    /**
     * 命中关键字的说明描述
     */
    @JsonProperty("wording")
    private String wording;
}
