package com.chia.multienty.core.parameter.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 功能权限列表获取请求
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "FuncPermissionListGetParameter",description = "功能权限列表获取请求")
public class FuncPermissionListGetParameter {
    private Long userId;
    private Long owner;
}
