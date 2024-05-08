package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信试用小程序注册审核单详情获取请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "WechatBetaMppRegisterAuditDetailGetParameter",description = "微信试用小程序注册审核单详情获取请求")
public class WechatBetaMppRegisterAuditDetailGetParameter {
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
