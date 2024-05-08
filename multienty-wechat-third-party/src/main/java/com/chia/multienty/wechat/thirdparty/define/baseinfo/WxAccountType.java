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
public enum WxAccountType {
    SUBSCRIBE(1, "订阅号"),
    SERVICE(2, "服务号"),
    MINI_PROGRAM(3, "小程序");
    @JsonValue
    private Integer value;
    private String describe;

    @JsonCreator
    public static WxAccountType valueOf(Integer value) {
        if(value == null) {
            return null;
        }
        for (WxAccountType type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
