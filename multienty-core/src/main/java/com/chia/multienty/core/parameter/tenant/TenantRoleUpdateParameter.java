package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 租户角色关联更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "TenantRoleUpdateParameter",description = "租户角色关联更新请求")
public class TenantRoleUpdateParameter {

    /**
     * 关联编号
     */
     @ApiModelProperty(value = "关联编号")
     @LogMetaId
     private Long trId;
    /**
     * 租户编号
     */
     @ApiModelProperty(value = "租户编号")
     private Long tenantId;
    /**
     * 角色编号
     */
     @ApiModelProperty(value = "角色编号")
     private Long roleId;
}
