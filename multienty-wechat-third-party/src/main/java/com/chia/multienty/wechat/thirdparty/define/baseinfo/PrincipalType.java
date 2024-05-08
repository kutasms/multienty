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
public enum PrincipalType {
    PERSONAL(0, "个人"),
    ENTERPRISE(1, "企业"),
    MEDIUM(2, "媒体"),
    GOVERNMENT(3, "政府"),
    OTHER(4, "其他");
    @JsonValue
    private Integer value;
    private String describe;

    @JsonCreator
    public static PrincipalType valueOf(Integer value) {
        if(value == null) {
            return null;
        }
        for (PrincipalType type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
