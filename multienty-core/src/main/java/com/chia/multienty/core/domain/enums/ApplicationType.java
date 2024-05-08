package com.chia.multienty.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationType {

    PLATFORM(1L, "平台","PLAT"),
    MERCHANT(2L, "商家","MERC"),
    WECHAT_MPP(3L, "微信小程序","WEMP"),
    NATIVE_APP(4L, "APP", "NAPP");

    @JsonValue
    private Long value;


    private String description;

    private String tokenPrefix;


    @JsonCreator
    public static ApplicationType valueOf(Long val) {
        for (ApplicationType mode : ApplicationType.values()) {
            if (val.equals(mode.getValue())) {
                return mode;
            }
        }
        return null;
    }
}
