package com.chia.multienty.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginMode {
    USERNAME_PASSWORD("用户名密码", 1),
    PHONE_CODE("手机验证码", 2);

    private String description;

    @JsonValue
    private Integer code;

    @JsonCreator
    public static LoginMode valueOf(Integer code) {
        if(code == null) {
            return null;
        }
        for (LoginMode type : LoginMode.values()) {
            if (code.equals(type.getCode())) {
                return type;
            }
        }
        return null;
    }
}
