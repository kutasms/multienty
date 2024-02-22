package com.chia.multienty.core.parameter.user;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 余额账单删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantSubAccountDeleteParameter",description = "余额账单删除请求")
public class TenantSubAccountDeleteParameter {

    /**
     * 子账号编号
     */
     @ApiModelProperty(value = "子账号编号")
     @LogMetaId
     private Long subAccountId;
}
