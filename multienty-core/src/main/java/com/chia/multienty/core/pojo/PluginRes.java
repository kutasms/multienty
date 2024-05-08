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
 * 插件资源
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_plugin_res")
@ApiModel(value="PluginRes对象", description="插件资源")
public class PluginRes extends KutaBaseEntity {


    /**
     * 插件资源编号
     */
    @ApiModelProperty(value = "插件资源编号")
    @TableId(value = "res_id", type = IdType.INPUT)
    private Long resId;

    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    @TableField("`plugin_id`")
    private Long pluginId;

    /**
     * 资源URL地址
     */
    @ApiModelProperty(value = "资源URL地址")
    @TableField("`res_url`")
    private String resUrl;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    @TableField("`file_name`")
    private String fileName;

    /**
     * 文件编号
     */
    @ApiModelProperty(value = "文件编号")
    @TableField("`file_id`")
    private Long fileId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
