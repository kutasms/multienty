package com.chia.multienty.wechat.thirdparty.sdk.response.oa;

import lombok.Data;

/**
 * 公众号授权链接生成请求响应数据
 */
@Data
public class OfficialAccountAuthLinkGenerateResponse {
    /**
     * 链接地址
     */
    private String linkUrl;
}
