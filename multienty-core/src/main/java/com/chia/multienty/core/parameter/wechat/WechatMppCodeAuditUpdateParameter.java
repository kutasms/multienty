package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信小程序代码审核单更新请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatMppCodeAuditUpdateParameter",description = "微信小程序代码审核单更新请求")
@Accessors(chain = true)
public class WechatMppCodeAuditUpdateParameter {

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
    /**
     * 商家小程序编号
     */
     @ApiModelProperty(value = "商家小程序编号")
     private String appId;
    /**
     * 错误码
     */
     @ApiModelProperty(value = "错误码")
     private Integer errorCode;
     private String errorMsg;
    /**
     * 审核成功时间
     */
     @ApiModelProperty(value = "审核成功时间")
     private LocalDateTime successTime;
    /**
     * 失败时间
     */
     @ApiModelProperty(value = "失败时间")
     private LocalDateTime failTime;
    /**
     * 审核延后时间
     */
     @ApiModelProperty(value = "审核延后时间")
     private LocalDateTime delayTime;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 审核不通过的原因
     */
     @ApiModelProperty(value = "审核不通过的原因")
     private String reason;
    /**
     * 截图
     */
     @ApiModelProperty(value = "截图")
     private String screenShot;
    /**
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
}
