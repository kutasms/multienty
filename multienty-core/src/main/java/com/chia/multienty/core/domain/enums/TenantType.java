package com.chia.multienty.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ApiModel(value = "TenantType", description = "租户类型")
public enum TenantType {
    COTENANCY(1, "cotenancy-tenant","合租租户"),
    ENTERPRISE(2,"enterprise-tenant","企业版租户"),
    FLAGSHIP(3,"flagship-tenant","旗舰版租户");
    @JsonValue
    private final Integer value;

    private final String configName;

    private final String description;

    public static TenantType valueOfConfigName(String configName) {
        if(configName == null) {
            return null;
        }
        for (TenantType mode : TenantType.values()) {
            if (configName.equals(mode.getConfigName())) {
                return mode;
            }
        }
        return null;
    }

    @JsonCreator
    public static TenantType valueOf(Integer val) {
        for (TenantType mode : TenantType.values()) {
            if (val.equals(mode.getValue())) {
                return mode;
            }
        }
        return null;
    }
}
