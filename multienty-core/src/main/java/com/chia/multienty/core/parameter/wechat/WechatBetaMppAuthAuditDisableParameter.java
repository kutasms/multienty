package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import com.chia.multienty.core.annotation.LogMetaId;
/**
 * <p>
 * 微信试用小程序转正审核单禁用请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */

@Data
@ApiModel(value = "WechatBetaMppAuthAuditDisableParameter",description = "微信试用小程序转正审核单禁用请求")
@Accessors(chain = true)
public class WechatBetaMppAuthAuditDisableParameter {
    /**
     * 审核编号
     */
     @ApiModelProperty(value = "审核编号")
     @LogMetaId
     private Long auditId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
