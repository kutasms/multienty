package com.chia.multienty.core.parameter.tenant;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户子账号启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-29
 */

@Data
@ApiModel(value = "TenantSubAccountEnableParameter",description = "租户子账号启用请求")
@Accessors(chain = true)
public class TenantSubAccountEnableParameter {
    /**
     * 子账号编号
     */
     @ApiModelProperty(value = "子账号编号")
     @LogMetaId
     private Long subAccountId;
}
