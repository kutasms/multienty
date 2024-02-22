package com.chia.multienty.core.parameter.user;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 租户子账号角色关联保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantSubAccountRoleSaveParameter",description = "租户子账号角色关联保存请求")
public class TenantSubAccountRoleSaveParameter {

    /**
     * 关联编号
     */
    @ApiModelProperty(value = "关联编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long sarId;
    /**
     * 租户子账号编号
     */
    @ApiModelProperty(value = "租户子账号编号")
    private Long subAccountId;
    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    private Long roleId;
}
