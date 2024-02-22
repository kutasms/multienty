package com.chia.multienty.wechat.thirdparty.define.register;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnterpriseCodeType {
    SOCIAL_CREDIT_CODE(1, "统一社会信用代码（18 位）"),
    ORGANIZATION_CODE(2, "组织机构代码（9 位 xxxxxxxx-x）"),
    BUSINESS_LICENSE(3, "营业执照注册号(15 位)");

    @JsonValue
    private Integer code;
    private String describe;


}
