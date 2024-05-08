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
 * 系统配置
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_setting")
@ApiModel(value="Setting对象", description="系统配置")
public class Setting extends KutaBaseEntity {


    /**
     * 配置编号
     */
    @ApiModelProperty(value = "配置编号")
    @TableId(value = "setting_id", type = IdType.AUTO)
    private Long settingId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @TableField("`name`")
    private String name;

    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    @TableField("`value`")
    private String value;

    /**
     * 默认值
     */
    @ApiModelProperty(value = "默认值")
    @TableField("`default_value`")
    private String defaultValue;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 拥有者
     */
    @ApiModelProperty(value = "拥有者")
    @TableField("`owner`")
    private Long owner;

    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
    @TableField("`app_id`")
    private Integer appId;


}
