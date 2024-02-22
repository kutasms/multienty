package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信小程序昵称审核单更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */

@Data
@ApiModel(value = "WechatMppNickNameAuditUpdateParameter",description = "微信小程序昵称审核单更新请求")
public class WechatMppNickNameAuditUpdateParameter {

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
     private Long tenantId;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 小程序appid
     */
     @ApiModelProperty(value = "小程序appid")
     private String appId;
    /**
     * 材料说明
     */
     @ApiModelProperty(value = "材料说明")
     private String wording;
    /**
     * 昵称
     */
     @ApiModelProperty(value = "昵称")
     private String nickName;
    /**
     * 驳回原因
     */
     @ApiModelProperty(value = "驳回原因")
     private String reason;
}
