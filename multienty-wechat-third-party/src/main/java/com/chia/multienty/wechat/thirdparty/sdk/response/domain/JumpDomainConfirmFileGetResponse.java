package com.chia.multienty.wechat.thirdparty.sdk.response.domain;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取业务域名校验文件响应数据
 */
@Data
public class JumpDomainConfirmFileGetResponse extends BaseResponse {
    /**
     * 文件名
     */
    @JsonProperty("file_name")
    private String fileName;
    /**
     * 文件内容
     */
    @JsonProperty("file_content")
    private String fileContent;
}
