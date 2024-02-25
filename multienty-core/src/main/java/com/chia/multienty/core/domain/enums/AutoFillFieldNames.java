package com.chia.multienty.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AutoFillFieldNames {
    CREATE_TIME("createTime"),
    UPDATE_TIME("updateTime"),
    VERSION("version"),
    DELETED("deleted"),
    STATUS("status");

    private String exp;


}
