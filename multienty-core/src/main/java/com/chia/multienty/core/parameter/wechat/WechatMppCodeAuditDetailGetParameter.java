package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信小程序代码审核单详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-27
 */

@Data
@ApiModel(value = "WechatMppCodeAuditDetailGetParameter",description = "微信小程序代码审核单详情获取请求")
public class WechatMppCodeAuditDetailGetParameter {
    /**
     * 审核单编号
     */
     @ApiModelProperty(value = "审核单编号")
     private Long auditId;
}
