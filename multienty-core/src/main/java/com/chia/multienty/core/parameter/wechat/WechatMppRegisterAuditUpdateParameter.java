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
 * 微信小程序注册审核单更新请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */

@Data
@ApiModel(value = "WechatMppRegisterAuditUpdateParameter",description = "微信小程序注册审核单更新请求")
@Accessors(chain = true)
public class WechatMppRegisterAuditUpdateParameter {

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
    /**
     * 商家小程序编号
     */
     @ApiModelProperty(value = "商家小程序编号")
     private String appId;
    /**
     * 错误码
     */
     @ApiModelProperty(value = "错误码")
     private Integer wxStatus;
    /**
     * 错误信息
     */
     @ApiModelProperty(value = "错误信息")
     private String authCode;
    /**
     * 授权链接
     */
     @ApiModelProperty(value = "授权链接")
     private String authUrl;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 提示信息
     */
     @ApiModelProperty(value = "提示信息")
     private String message;
    /**
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 企业名称
     */
     @ApiModelProperty(value = "企业名称")
     private String name;
    /**
     * 企业代码
     */
     @ApiModelProperty(value = "企业代码")
     private String code;
    /**
     * 企业代码类型
     */
     @ApiModelProperty(value = "企业代码类型")
     private Integer codeType;
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
     * 错误代码
     */
     @ApiModelProperty(value = "错误代码")
     private Integer errorCode;
    /**
     * 错误信息
     */
     @ApiModelProperty(value = "错误信息")
     private String errorMsg;
}
