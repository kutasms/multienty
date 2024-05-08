package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 上传提审素材响应数据
 */
@Data
public class MediaToCodeAuditUploadResponse extends BaseResponse {
    /**
     * 类型
     */
    @JsonProperty("type")
    private String type;
    /**
     * 媒体id
     */
    @JsonProperty(value = "mediaid")
    private Long mediaId;
}
