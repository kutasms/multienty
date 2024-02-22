package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信小程序代码审核单启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WechatMppCodeAuditEnableParameter",description = "微信小程序代码审核单启用请求")
public class WechatMppCodeAuditEnableParameter {
    /**
     * 审核单编号
     */
     @ApiModelProperty(value = "审核单编号")
     private Long auditId;
}
