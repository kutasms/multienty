package com.chia.multienty.core.parameter.user;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 余额账单启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantSubAccountEnableParameter",description = "余额账单启用请求")
public class TenantSubAccountEnableParameter {
    /**
     * 子账号编号
     */
     @ApiModelProperty(value = "子账号编号")
     private Long subAccountId;
}
