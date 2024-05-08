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
 * 微信小程序认证任务
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_mpp_auth_task")
@ApiModel(value="WechatMppAuthTask对象", description="微信小程序认证任务")
public class WechatMppAuthTask extends KutaBaseEntity {


    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    @TableId(value = "task_id", type = IdType.INPUT)
    private Long taskId;

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
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    @TableField("`task_status`")
    private Integer taskStatus;

    /**
     * 审核单状态
     */
    @ApiModelProperty(value = "审核单状态")
    @TableField("`apply_status`")
    private Integer applyStatus;

    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    @TableField("`params`")
    private String params;

    /**
     * 审核机构名称
     */
    @ApiModelProperty(value = "审核机构名称")
    @TableField("`dispatch_info_provider`")
    private String dispatchInfoProvider;

    /**
     * 失败时间
     */
    @ApiModelProperty(value = "失败时间")
    @TableField("`dispatch_info_contact`")
    private String dispatchInfoContact;

    /**
     * 派单时间戳（秒）
     */
    @ApiModelProperty(value = "派单时间戳（秒）")
    @TableField("`dispatch_info_dispatch_time`")
    private Long dispatchInfoDispatchTime;

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
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "`update_time`", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;


}
