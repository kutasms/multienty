package com.chia.multienty.core.parameter.user;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 账户关联角色信息表更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "UserRoleUpdateParameter",description = "账户关联角色信息表更新请求")
public class UserRoleUpdateParameter {

    /**
     * 用户角色关联编号
     */
     @ApiModelProperty(value = "用户角色关联编号")
     @LogMetaId
     private Long urId;
    /**
     * 账户id
     */
     @ApiModelProperty(value = "账户id")
     private Long userId;
    /**
     * 关联角色表id
     */
     @ApiModelProperty(value = "关联角色表id")
     private Long roleId;
}
