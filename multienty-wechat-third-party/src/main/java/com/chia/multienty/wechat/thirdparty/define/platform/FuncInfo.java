package com.chia.multienty.wechat.thirdparty.define.platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 授权给第三方平台的权限集id列表，权限集id的含义可查看权限集介绍
 */
@Data
public class FuncInfo {
    /**
     * 授权给开发者的权限集详情
     */
    @JsonProperty(value = "funcscope_category")
    private FuncScopeCategory funcScopeCategory;
}
