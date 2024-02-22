package com.chia.multienty.wechat.thirdparty.define.platform;

import lombok.Data;

/**
 * 授权给开发者的权限集详情
 */
@Data
public class FuncScopeCategory {
    /**
     * 权限集id
     */
    private Integer id;
    /**
     * 权限集类型
     */
    private Integer type;
    /**
     * 权限集名称
     */
    private String name;
    /**
     * 权限集描述
     */
    private String desc;
}
