package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信试用小程序转正审核单保存请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */

@Data
@ApiModel(value = "WechatBetaMppAuthAuditSaveParameter",description = "微信试用小程序转正审核单保存请求")
@Accessors(chain = true)
public class WechatBetaMppAuthAuditSaveParameter {

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
    @NotNull
    private Long tenantId;
    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    private String uniqueId;
    /**
     * 商家小程序编号
     */
    @ApiModelProperty(value = "商家小程序编号")
    private String appId;
    /**
     * 任务状态码
     */
    @ApiModelProperty(value = "任务状态码")
    private Integer taskStatus;
    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    private String params;
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
     * 消息
     */
    @ApiModelProperty(value = "消息")
    private String message;
    /**
     * 法人微信号
     */
    @ApiModelProperty(value = "法人微信号")
    private String legalPersonaWechat;
    /**
     * 法人姓名
     */
    @ApiModelProperty(value = "法人姓名")
    private String legalPersonaName;
    /**
     * 第三方联系电话
     */
    @ApiModelProperty(value = "第三方联系电话")
    private String componentPhone;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    /**
     * 企业代码
     */
    @ApiModelProperty(value = "企业代码")
    private String companyCode;
}
