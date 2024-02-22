package com.chia.multienty.core.domain.enums;

import com.chia.multienty.core.exception.KutaRuntimeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VisitSource {
    MP_WEIXIN(1, "微信小程序"),
    MP_ALIPAY(2,"支付宝小程序"),
    H5(3,"手机浏览器"),
    PC(4,"网页浏览器"),
    APP(5,"手机APP");

    @JsonValue
    private Integer value;
    private String name;

    @JsonCreator
    public static VisitSource parse(Integer code) throws KutaRuntimeException {
        if(code == null) {
            return null;
        }
        for(VisitSource item : values()) {
            if(item.getValue().equals(code)) {
                return item;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ARGUMENT_ERROR);
    }
}
