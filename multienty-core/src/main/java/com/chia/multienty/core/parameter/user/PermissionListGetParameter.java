package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.pojo.Role;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 权限列表获取请求
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PermissionListGetParameter",description = "权限列表获取请求")
public class PermissionListGetParameter {
    private Long userId;
    private Long owner;
    private List<Role> roles;
}
