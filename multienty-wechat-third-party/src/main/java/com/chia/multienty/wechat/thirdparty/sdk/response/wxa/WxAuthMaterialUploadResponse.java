package com.chia.multienty.wechat.thirdparty.sdk.response.wxa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序认证上传补充材料响应数据
 */
@Data
public class WxAuthMaterialUploadResponse extends BaseResponse {
    /**
     * 类型
     */
    @JsonProperty("type")
    private String type;
    /**
     * 媒体id
     */
    @JsonProperty(value = "mediaid")
    private String mediaId;
}
