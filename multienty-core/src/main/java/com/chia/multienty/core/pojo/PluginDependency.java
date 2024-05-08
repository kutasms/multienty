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
 * 插件依赖
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_plugin_dependency")
@ApiModel(value="PluginDependency对象", description="插件依赖")
public class PluginDependency extends KutaBaseEntity {


    /**
     * 插件依赖编号
     */
    @ApiModelProperty(value = "插件依赖编号")
    @TableId(value = "dependency_id", type = IdType.INPUT)
    private Long dependencyId;

    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    @TableField("`plugin_id`")
    private Long pluginId;

    /**
     * 依赖资源名
     */
    @ApiModelProperty(value = "依赖资源名")
    @TableField("`dep_res_name`")
    private Integer depResName;

    /**
     * 文件扩展名
     */
    @ApiModelProperty(value = "文件扩展名")
    @TableField("`ext_name`")
    private Integer extName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
