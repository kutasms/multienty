package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 获取隐私接口检测结果响应数据
 */
@Data
public class CodePrivacyInfoGetResponse extends BaseResponse {
    /**
     * 没权限的隐私接口的api英文名
     */
    @JsonProperty(value = "without_auth_list")
    private List<String> withoutAuthList;
    /**
     * 没配置的隐私接口的api英文名
     */
    @JsonProperty(value = "without_conf_list")
    private List<String> withoutConfigList;
}
