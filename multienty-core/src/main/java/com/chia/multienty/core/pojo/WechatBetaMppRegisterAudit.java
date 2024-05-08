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
 * 微信试用小程序注册审核单
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_beta_mpp_register_audit")
@ApiModel(value="WechatBetaMppRegisterAudit对象", description="微信试用小程序注册审核单")
public class WechatBetaMppRegisterAudit extends KutaBaseEntity {


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
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    @TableField("`unique_id`")
    private String uniqueId;

    /**
     * 商家小程序编号
     */
    @ApiModelProperty(value = "商家小程序编号")
    @TableField("`app_id`")
    private String appId;

    /**
     * 任务状态码
     */
    @ApiModelProperty(value = "任务状态码")
    @TableField("`task_status`")
    private Integer taskStatus;

    /**
     * 小程序名称
     */
    @ApiModelProperty(value = "小程序名称")
    @TableField("`name`")
    private String name;

    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码")
    @TableField("`error_code`")
    private Integer errorCode;

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息")
    @TableField("`error_msg`")
    private String errorMsg;

    /**
     * 授权链接
     */
    @ApiModelProperty(value = "授权链接")
    @TableField("`authorize_url`")
    private String authorizeUrl;

    /**
     * 微信用户openid
     */
    @ApiModelProperty(value = "微信用户openid")
    @TableField("`open_id`")
    private String openId;

    /**
     * 微信用户unionid
     */
    @ApiModelProperty(value = "微信用户unionid")
    @TableField("`union_id`")
    private String unionId;

    /**
     * 消息
     */
    @ApiModelProperty(value = "消息")
    @TableField("`message`")
    private String message;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
