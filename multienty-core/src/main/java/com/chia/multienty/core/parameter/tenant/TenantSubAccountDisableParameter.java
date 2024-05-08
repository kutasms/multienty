package com.chia.multienty.core.parameter.tenant;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户子账号禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-29
 */

@Data
@ApiModel(value = "TenantSubAccountDisableParameter",description = "租户子账号禁用请求")
@Accessors(chain = true)
public class TenantSubAccountDisableParameter {
    /**
     * 子账号编号
     */
     @ApiModelProperty(value = "子账号编号")
     @LogMetaId
     private Long subAccountId;
}
