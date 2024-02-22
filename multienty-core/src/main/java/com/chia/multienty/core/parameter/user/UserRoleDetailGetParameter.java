package com.chia.multienty.core.parameter.user;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 账户关联角色信息表详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "UserRoleDetailGetParameter",description = "账户关联角色信息表详情获取请求")
public class UserRoleDetailGetParameter {
    /**
     * 用户角色关联编号
     */
     @ApiModelProperty(value = "用户角色关联编号")
     private Long urId;
}
