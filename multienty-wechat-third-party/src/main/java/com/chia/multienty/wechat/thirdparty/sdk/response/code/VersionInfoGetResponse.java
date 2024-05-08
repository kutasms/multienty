package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.code.CodeExpInfo;
import com.chia.multienty.wechat.thirdparty.define.code.CodeReleaseInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 查询小程序版本信息响应数据
 */
@Data
public class VersionInfoGetResponse extends BaseResponse {
    /**
     * 体验版信息
     */
    @JsonProperty("exp_info")
    private CodeExpInfo expInfo;
    /**
     * 线上版信息
     */
    @JsonProperty("release_info")
    private CodeReleaseInfo releaseInfo;
}
