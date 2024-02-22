package com.chia.multienty.core.parameter.user;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 角色删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "RoleDeleteParameter",description = "角色删除请求")
public class RoleDeleteParameter {

    /**
     * 角色编号
     */
     @ApiModelProperty(value = "角色编号")
     @LogMetaId
     private Long roleId;
}
