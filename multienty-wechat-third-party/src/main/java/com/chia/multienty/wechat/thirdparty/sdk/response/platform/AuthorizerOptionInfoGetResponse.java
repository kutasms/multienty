package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取授权方选项信息返回数据
 */
@Data
public class AuthorizerOptionInfoGetResponse extends BaseResponse {
    /**
     * 选项名称
     */
    @JsonProperty("option_name")
    private String optionName;
    /**
     * 选项值
     */
    @JsonProperty("option_value")
    private String optionValue;
}
