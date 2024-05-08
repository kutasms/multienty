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
 * 插件统计
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_plugin_stat")
@ApiModel(value="PluginStat对象", description="插件统计")
public class PluginStat extends KutaBaseEntity {


    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    @TableId(value = "plugin_id", type = IdType.INPUT)
    private Long pluginId;

    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数")
    @TableField("`star_count`")
    private Integer starCount;

    /**
     * 关注人数
     */
    @ApiModelProperty(value = "关注人数")
    @TableField("`watch_count`")
    private Integer watchCount;

    /**
     * 应用次数
     */
    @ApiModelProperty(value = "应用次数")
    @TableField("`apply_count`")
    private Integer applyCount;

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
     * 乐观锁版本号
     */
    @ApiModelProperty(value = "乐观锁版本号")
    @TableField(value = "`version`", fill = FieldFill.INSERT)
    private Long version;


}
