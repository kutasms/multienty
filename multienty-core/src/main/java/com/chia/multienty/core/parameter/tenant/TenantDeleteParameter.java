package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 租户删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantDeleteParameter",description = "租户删除请求")
public class TenantDeleteParameter {

    /**
     * 租户编号
     */
     @ApiModelProperty(value = "租户编号")
     @LogMetaId
     private Long tenantId;
}
