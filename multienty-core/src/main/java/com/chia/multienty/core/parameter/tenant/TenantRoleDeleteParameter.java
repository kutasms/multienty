package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 租户角色关联删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantRoleDeleteParameter",description = "租户角色关联删除请求")
public class TenantRoleDeleteParameter {

    /**
     * 关联编号
     */
     @ApiModelProperty(value = "关联编号")
     @LogMetaId
     private Long trId;
}
