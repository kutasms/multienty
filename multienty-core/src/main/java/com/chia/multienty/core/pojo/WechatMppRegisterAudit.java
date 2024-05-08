package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信小程序注册审核单
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_mpp_register_audit")
@ApiModel(value="WechatMppRegisterAudit对象", description="微信小程序注册审核单")
public class WechatMppRegisterAudit extends KutaBaseEntity {


    /**
     * 审核编号
     */
    @ApiModelProperty(value = "审核编号")
    @TableId(value = "audit_id", type = IdType.AUTO)
    private Long auditId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField("`tenant_id`")
    private Long tenantId;

    /**
     * 商家小程序编号
     */
    @ApiModelProperty(value = "商家小程序编号")
    @TableField("`app_id`")
    private String appId;

    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码")
    @TableField("`wx_status`")
    private Integer wxStatus;

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息")
    @TableField("`auth_code`")
    private String authCode;

    /**
     * 授权链接
     */
    @ApiModelProperty(value = "授权链接")
    @TableField("`auth_url`")
    private String authUrl;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    @TableField("`message`")
    private String message;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @TableField("`name`")
    private String name;

    /**
     * 企业代码
     */
    @ApiModelProperty(value = "企业代码")
    @TableField("`code`")
    private String code;

    /**
     * 企业代码类型
     */
    @ApiModelProperty(value = "企业代码类型")
    @TableField("`code_type`")
    private Integer codeType;

    /**
     * 法人微信号
     */
    @ApiModelProperty(value = "法人微信号")
    @TableField("`legal_persona_wechat`")
    private String legalPersonaWechat;

    /**
     * 法人姓名
     */
    @ApiModelProperty(value = "法人姓名")
    @TableField("`legal_persona_name`")
    private String legalPersonaName;

    /**
     * 第三方联系电话
     */
    @ApiModelProperty(value = "第三方联系电话")
    @TableField("`component_phone`")
    private String componentPhone;

    /**
     * 错误代码
     */
    @ApiModelProperty(value = "错误代码")
    @TableField("`error_code`")
    private Integer errorCode;

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息")
    @TableField("`error_msg`")
    private String errorMsg;


}
