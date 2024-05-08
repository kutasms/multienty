package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 微信小程序代码审核单
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_mpp_code_audit")
@ApiModel(value="WechatMppCodeAudit对象", description="微信小程序代码审核单")
public class WechatMppCodeAudit extends KutaBaseEntity {


    /**
     * 审核单编号
     */
    @ApiModelProperty(value = "审核单编号")
    @TableId(value = "audit_id", type = IdType.INPUT)
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
    @TableField("`error_code`")
    private Integer errorCode;

    @TableField("`error_msg`")
    private String errorMsg;

    /**
     * 审核成功时间
     */
    @ApiModelProperty(value = "审核成功时间")
    @TableField("`success_time`")
    private LocalDateTime successTime;

    /**
     * 失败时间
     */
    @ApiModelProperty(value = "失败时间")
    @TableField("`fail_time`")
    private LocalDateTime failTime;

    /**
     * 审核延后时间
     */
    @ApiModelProperty(value = "审核延后时间")
    @TableField("`delay_time`")
    private LocalDateTime delayTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 审核不通过的原因
     */
    @ApiModelProperty(value = "审核不通过的原因")
    @TableField("`reason`")
    private String reason;

    /**
     * 截图
     */
    @ApiModelProperty(value = "截图")
    @TableField("`screen_shot`")
    private String screenShot;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
