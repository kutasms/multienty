package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_dict")
@ApiModel(value="Dict对象", description="数据字典")
public class Dict extends KutaBaseEntity {


    /**
     * 字典编号
     */
    @ApiModelProperty(value = "字典编号")
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;

    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级编号")
    @TableField("`pid`")
    private Long pid;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    @TableField("`label`")
    private String label;

    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    @TableField("`alias`")
    private String alias;

    /**
     * 字典值
     */
    @ApiModelProperty(value = "字典值")
    @TableField("`value`")
    private String value;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("`remark`")
    private String remark;

    /**
     * 值类型
     */
    @ApiModelProperty(value = "值类型")
    @TableField("`value_type`")
    private String valueType;

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
     * 是否可删除
     */
    @ApiModelProperty(value = "是否可删除")
    @TableField("`deletable`")
    private Boolean deletable;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 乐观锁版本号
     */
    @ApiModelProperty(value = "乐观锁版本号")
    @TableField(value = "`version`", fill = FieldFill.INSERT)
    private Long version;

    /**
     * 是否已加密
     */
    @ApiModelProperty(value = "是否已加密")
    @TableField("`encrypted`")
    private Boolean encrypted;


}
