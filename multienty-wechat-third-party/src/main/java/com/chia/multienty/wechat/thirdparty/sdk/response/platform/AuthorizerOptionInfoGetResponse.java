package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import lombok.Data;

/**
 * 获取授权方选项信息返回数据
 */
@Data
public class AuthorizerOptionInfoGetResponse {
    /**
     * 选项名称
     */
    private String optionName;
    /**
     * 选项值
     */
    private String optionValue;
}
