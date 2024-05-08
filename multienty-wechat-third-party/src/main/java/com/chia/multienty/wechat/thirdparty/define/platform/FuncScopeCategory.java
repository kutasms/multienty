package com.chia.multienty.wechat.thirdparty.define.platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 授权给开发者的权限集详情
 */
@Data
public class FuncScopeCategory {
    /**
     * 权限集id
     */
    @JsonProperty("id")
    private Integer id;
    /**
     * 权限集类型
     */
    @JsonProperty("type")
    private Integer type;
    /**
     * 权限集名称
     */
    @JsonProperty("name")
    private String name;
    /**
     * 权限集描述
     */
    @JsonProperty("desc")
    private String desc;
}
