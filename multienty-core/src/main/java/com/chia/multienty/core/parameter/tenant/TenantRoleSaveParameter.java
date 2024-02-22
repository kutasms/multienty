package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 租户角色关联保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "TenantRoleSaveParameter",description = "租户角色关联保存请求")
public class TenantRoleSaveParameter {

    /**
     * 关联编号
     */
    @ApiModelProperty(value = "关联编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
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
