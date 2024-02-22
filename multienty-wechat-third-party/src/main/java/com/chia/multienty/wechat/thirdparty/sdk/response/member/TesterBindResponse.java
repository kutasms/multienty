package com.chia.multienty.wechat.thirdparty.sdk.response.member;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 绑定体验者响应数据
 */
@Data
public class TesterBindResponse extends BaseResponse {
    /**
     * 人员对应的唯一字符串
     */
    @JsonProperty(value = "userstr")
    private String userStr;
}
