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
public enum RealNameStatus {
    SUCCESS(1, "实名验证成功"),
    VERIFYING(2, "实名验证中"),
    FAILURE(3, "实名验证失败");
    @JsonValue
    private Integer value;
    private String describe;

    @JsonCreator
    public static RealNameStatus valueOf(Integer value) {
        if(value == null) {
            return null;
        }
        for (RealNameStatus type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
