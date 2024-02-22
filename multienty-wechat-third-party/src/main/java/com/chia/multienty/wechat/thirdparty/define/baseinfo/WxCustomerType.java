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
public enum WxCustomerType {
    ENTERPRISE(1, "企业"),
    MEDIUM(2, "企业媒体"),
    GOVERNMENT(3, "政府"),
    NON_PROFIT_ORG(4, "非盈利组织"),
    NON_ENTERPRISE(5,"民营非企业"),
    PROFIT_ORG(6, "盈利组织"),
    SOCIAL_ORG(8, "社会团体"),
    BUSINESS_MEDIUM(9, "事业媒体"),
    PUBLIC_INSTITUTION(11, "事业单位"),
    INDIVIDUAL_BUSINESS(12, "个体工商户"),
    OVERSEAS_ENTERPRISE(14, "海外企业"),
    PERSONAL(15, "个人");
    @JsonValue
    private Integer value;
    private String describe;

    @JsonCreator
    public static WxCustomerType valueOf(Integer value) {
        if(value == null) {
            return null;
        }
        for (WxCustomerType type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
