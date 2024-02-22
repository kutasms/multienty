package com.chia.multienty.core.parameter.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 功能权限列表获取请求
 */
@Data
@ApiModel(value = "FuncPermissionListGetParameter",description = "功能权限列表获取请求")
public class FuncPermissionListGetParameter {
    private Long userId;
    private Long owner;
}
