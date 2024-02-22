package com.chia.multienty.core.parameter.user;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 余额账单禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantSubAccountDisableParameter",description = "余额账单禁用请求")
public class TenantSubAccountDisableParameter {
    /**
     * 子账号编号
     */
     @ApiModelProperty(value = "子账号编号")
     private Long subAccountId;
}
