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
public enum AuditStatus {
    AUDITING(1, "审核中"),
    FAILURE(2, "审核失败"),
    SUCCESS(3, "审核成功");
    @JsonValue
    private Integer value;
    private String describe;

    @JsonCreator
    public static AuditStatus valueOf(Integer value) {
        if(value == null) {
            return null;
        }
        for (AuditStatus type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
