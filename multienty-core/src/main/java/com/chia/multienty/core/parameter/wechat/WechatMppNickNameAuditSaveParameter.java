package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信小程序昵称审核单保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */

@Data
@ApiModel(value = "WechatMppNickNameAuditSaveParameter",description = "微信小程序昵称审核单保存请求")
public class WechatMppNickNameAuditSaveParameter {

    /**
     * 审核单编号
     */
    @ApiModelProperty(value = "审核单编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long auditId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    private Long tenantId;
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
