package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.define.platform.SimpleAuthorizationInfo;
import lombok.Data;

import java.util.List;

/**
 * 拉取已授权的账号信息响应数据
 */
@Data
public class AuthorizerListGetResponse {
    private Integer totalCount;

    private List<SimpleAuthorizationInfo> list;
}
