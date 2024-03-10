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
 * 微信小程序代码审核单
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_mpp_register_audit")
@ApiModel(value="WechatMppRegisterAudit对象", description="微信小程序代码审核单")
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
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    @TableField("`task_id`")
    private String taskId;

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
    @TableField("`auth_url`")
    private String authUrl;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 任务状态码
     */
    @ApiModelProperty(value = "任务状态码")
    @TableField("`task_status`")
    private Integer taskStatus;

    /**
     * 审核单状态
     */
    @ApiModelProperty(value = "审核单状态")
    @TableField("`apply_status`")
    private Integer applyStatus;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    @TableField("`message`")
    private String message;

    /**
     * 审核机构名称
     */
    @ApiModelProperty(value = "审核机构名称")
    @TableField("`provider`")
    private String provider;

    /**
     * 审核机构联系方式
     */
    @ApiModelProperty(value = "审核机构联系方式")
    @TableField("`contact`")
    private String contact;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 派单时间
     */
    @ApiModelProperty(value = "派单时间")
    @TableField("`dispatch_time`")
    private LocalDateTime dispatchTime;


}
