package com.chia.multienty.wechat.thirdparty.sdk.response.domain;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 获取第三方平台业务域名校验文件响应数据
 */
@Data
public class ThirdPartyJumpDomainConfirmFileGetResponse extends BaseResponse {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件内容
     */
    private String fileContent;
}
