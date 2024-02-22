package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信小程序代码审核单删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WechatMppRegisterAuditDeleteParameter",description = "微信小程序代码审核单删除请求")
public class WechatMppRegisterAuditDeleteParameter {

    /**
     * 审核编号
     */
     @ApiModelProperty(value = "审核编号")
     @LogMetaId
     private Long auditId;
}
