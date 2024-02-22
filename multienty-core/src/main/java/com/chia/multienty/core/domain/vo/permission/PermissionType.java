package com.chia.multienty.core.domain.vo.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PermissionType {
    MENU(1, "目录/菜单"),
    INTERACTIVE(2,"交互行为");

    private Integer code;
    private String name;

    public static PermissionType valueOf(Integer code) {
       return Arrays.stream(PermissionType.values()).filter(p-> p.getCode().equals(code)).findAny().orElse(null);
    }
}
