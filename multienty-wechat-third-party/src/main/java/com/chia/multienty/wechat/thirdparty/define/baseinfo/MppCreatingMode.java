package com.chia.multienty.wechat.thirdparty.define.baseinfo;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信账号类型
 */
@Getter
@AllArgsConstructor
public enum MppCreatingMode {
    REG_BETA_MPP(1, "注册试用小程序"),
    REG_FROM_OFFICIAL_ACC(2, "从已有公众号创建"),
    REG_ENTERPRISE_MPP(3, "注册企业小程序"),
    BIND_EXISTS_MPP(4, "绑定已有小程序");
    @JsonValue
    private Integer value;
    private String describe;

    @JsonCreator
    public static MppCreatingMode valueOf(Integer value) {
        if(value == null) {
            return null;
        }
        for (MppCreatingMode type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
