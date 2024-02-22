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
 * 关键词
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_word")
@ApiModel(value="Word对象", description="关键词")
public class Word extends KutaBaseEntity {


    /**
     * 关键词编号
     */
    @ApiModelProperty(value = "关键词编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关键词内容
     */
    @ApiModelProperty(value = "关键词内容")
    @TableField("`keyword`")
    private String keyword;

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

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "`update_time`", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @TableField("`type`")
    private String type;

    /**
     * 是否已删除
     */
    @ApiModelProperty(value = "是否已删除")
    @TableField(value = "`deleted`", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


}
