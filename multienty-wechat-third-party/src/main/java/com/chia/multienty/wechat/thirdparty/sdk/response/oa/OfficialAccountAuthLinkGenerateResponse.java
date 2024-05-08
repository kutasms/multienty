package com.chia.multienty.wechat.thirdparty.sdk.response.oa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 公众号授权链接生成请求响应数据
 */
@Data
public class OfficialAccountAuthLinkGenerateResponse extends BaseResponse {
    /**
     * 链接地址
     */
    private String linkUrl;
}
