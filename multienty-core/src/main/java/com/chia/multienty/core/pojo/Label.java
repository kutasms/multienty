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
 * 标签
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_label")
@ApiModel(value="Label对象", description="标签")
public class Label extends KutaBaseEntity {


    /**
     * 标签编号
     */
    @ApiModelProperty(value = "标签编号")
    @TableId(value = "label_id", type = IdType.AUTO)
    private Long labelId;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    @TableField("`label`")
    private String label;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @TableField("`type`")
    private Short type;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

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

    /**
     * 审核失败原因
     */
    @ApiModelProperty(value = "审核失败原因")
    @TableField("`fail_reason`")
    private String failReason;


}
