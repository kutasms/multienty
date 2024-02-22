package com.chia.multienty.core.parameter.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "UserPasswordUpdateParameter",description = "用户密码更新请求")
public class UserPasswordUpdateParameter {
    @NotBlank
    @Length(min = 4, max = 4)
    private String verificationCode;
    @NotBlank
    @Length(min = 6)
    private String password;
    @NotNull
    private Long userId;
}
