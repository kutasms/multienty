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
 * 微信小程序代码审核单启用请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatMppCodeAuditEnableParameter",description = "微信小程序代码审核单启用请求")
@Accessors(chain = true)
public class WechatMppCodeAuditEnableParameter {
    /**
     * 审核单编号
     */
     @ApiModelProperty(value = "审核单编号")
     @LogMetaId
     private Long auditId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
