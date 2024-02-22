package com.chia.multienty.core.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserBoType {
    STATUS_CHANGED(1, "管理人员状态变更通知");
    private Integer code;
    private String desc;
}
