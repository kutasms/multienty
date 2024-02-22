package com.chia.multienty.core.parameter.user;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 角色信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "RoleDetailGetParameter",description = "角色信息详情获取请求")
public class RoleDetailGetParameter {
    /**
     * 角色id
     */
     @ApiModelProperty(value = "角色id")
     private Long roleId;
}
