package com.chia.multienty.core.parameter.user;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 余额账单更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantSubAccountUpdateParameter",description = "余额账单更新请求")
public class TenantSubAccountUpdateParameter {

    /**
     * 子账号编号
     */
     @ApiModelProperty(value = "子账号编号")
     @LogMetaId
     private Long subAccountId;
    /**
     * 租户编号
     */
     @ApiModelProperty(value = "租户编号")
     private Long tenantId;
    /**
     * 手机号码
     */
     @ApiModelProperty(value = "手机号码")
     private String phoneNumber;
    /**
     * 姓名
     */
     @ApiModelProperty(value = "姓名")
     private String name;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 乐观锁版本号
     */
     @ApiModelProperty(value = "乐观锁版本号")
     private Long version;
    /**
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
}
