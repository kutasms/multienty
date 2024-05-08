package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import javax.validation.constraints.NotNull;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户子账号角色关联删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-29
 */

@Data
@ApiModel(value = "TenantSubAccountRoleDeleteParameter",description = "租户子账号角色关联删除请求")
@Accessors(chain = true)
public class TenantSubAccountRoleDeleteParameter {

    /**
     * 关联编号
     */
    @ApiModelProperty(value = "关联编号")
    @LogMetaId
    private Long sarId;
    /**
     * 租户子账号编号
     */
    @ApiModelProperty(value = "租户子账号编号")
    @LogMetaId
    private Long subAccountId;
    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    @LogMetaId
    private Long roleId;

}
