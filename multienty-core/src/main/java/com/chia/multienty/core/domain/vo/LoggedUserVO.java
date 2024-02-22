package com.chia.multienty.core.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 已登录用户VO
 * */
@Data
@Builder
public class LoggedUserVO {
    /**
     * 头像
     * */
    private String avatar;
    /**
     * 名称
     * */
    private String username;

    private Boolean superAdmin;

    /**
     * 角色列表
     * */
    private List<String> roles;
    /**
     * 权限列表
     * */
    private List<String> permissions;
}
