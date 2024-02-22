package com.chia.multienty.core.parameter.user;

import lombok.Data;

/**
 * 平台用户获取请求
 * */
@Data
public class UserGetByTokenParameter {
    /**
     * 鉴权TOKEN
     * */
    private String token;
}
