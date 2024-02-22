package com.chia.multienty.core.parameter.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 登录验证码发送请求
 */
@Data
@ApiModel(value = "LoginVerificationCodeSendParameter",description = "登录验证码发送请求")
public class LoginVerificationCodeSendParameter {
    private String phoneNumber;
}
