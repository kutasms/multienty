package com.chia.multienty.core.parameter.user;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 余额账单详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantSubAccountDetailGetParameter",description = "余额账单详情获取请求")
public class TenantSubAccountDetailGetParameter {
    /**
     * 子账号编号
     */
     @ApiModelProperty(value = "子账号编号")
     private Long subAccountId;
}
