package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信小程序代码审核单保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-27
 */

@Data
@ApiModel(value = "WechatMppRegisterAuditSaveParameter",description = "微信小程序代码审核单保存请求")
public class WechatMppRegisterAuditSaveParameter {

    /**
     * 审核编号
     */
    @ApiModelProperty(value = "审核编号")
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
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    private String taskId;
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
    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息")
    private String errorMsg;
    /**
     * 授权链接
     */
    @ApiModelProperty(value = "授权链接")
    private String authUrl;
    /**
     * 任务状态码
     */
    @ApiModelProperty(value = "任务状态码")
    private Integer taskStatus;
    /**
     * 审核单状态
     */
    @ApiModelProperty(value = "审核单状态")
    private Integer applyStatus;
    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    private String message;
    /**
     * 审核机构名称
     */
    @ApiModelProperty(value = "审核机构名称")
    private String provider;
    /**
     * 审核机构联系方式
     */
    @ApiModelProperty(value = "审核机构联系方式")
    private String contact;
    /**
     * 派单时间
     */
    @ApiModelProperty(value = "派单时间")
    private LocalDateTime dispatchTime;
}
