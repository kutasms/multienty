package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户子账号角色关联详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-29
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "TenantSubAccountRoleDetailGetParameter",description = "租户子账号角色关联详情获取请求")
public class TenantSubAccountRoleDetailGetParameter {
    /**
     * 关联编号
     */
     @ApiModelProperty(value = "关联编号")
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
