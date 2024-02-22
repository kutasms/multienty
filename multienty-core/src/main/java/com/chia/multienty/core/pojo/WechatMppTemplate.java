package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信小程序模版
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_mpp_template")
@ApiModel(value="WechatMppTemplate对象", description="微信小程序模版")
public class WechatMppTemplate extends KutaBaseEntity {


    /**
     * 微信小程序模版编号
     */
    @ApiModelProperty(value = "微信小程序模版编号")
    @TableId(value = "mpp_template_id", type = IdType.AUTO)
    private Long mppTemplateId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField("`tenant_id`")
    private Long tenantId;

    /**
     * 模版类型
     */
    @ApiModelProperty(value = "模版类型")
    @TableField("`type`")
    private Short type;

    /**
     * 微信小程序编号
     */
    @ApiModelProperty(value = "微信小程序编号")
    @TableField("`program_id`")
    private Long programId;

    /**
     * 模版编号
     */
    @ApiModelProperty(value = "模版编号")
    @TableField("`template_id`")
    private String templateId;

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

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 是否已删除
     */
    @ApiModelProperty(value = "是否已删除")
    @TableField(value = "`deleted`", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


}
