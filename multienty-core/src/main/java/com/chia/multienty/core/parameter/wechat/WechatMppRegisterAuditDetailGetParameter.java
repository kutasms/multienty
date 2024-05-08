package com.chia.multienty.core.parameter.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
/**
 * <p>
 * 微信小程序注册审核单详情获取请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "WechatMppRegisterAuditDetailGetParameter",description = "微信小程序注册审核单详情获取请求")
public class WechatMppRegisterAuditDetailGetParameter {
    /**
     * 审核编号
     */
     @ApiModelProperty(value = "审核编号")
     private Long auditId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
