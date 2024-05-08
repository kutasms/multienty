package com.chia.multienty.core.parameter.plugin;

import com.chia.multienty.core.annotation.LogMetaId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 插件保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginSaveParameter",description = "插件保存请求")
@Accessors(chain = true)
public class PluginSaveParameter {

    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long pluginId;
    /**
     * 插件名称
     */
    @ApiModelProperty(value = "插件名称")
    private String name;
    /**
     * 插件类型
     */
    @ApiModelProperty(value = "插件类型")
    private Short type;
    /**
     * 插件类型名称
     */
    @ApiModelProperty(value = "插件类型名称")
    private String typeName;
    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    private String alias;
    /**
     * 详情
     */
    @ApiModelProperty(value = "详情")
    private String details;
    /**
     * 运行模式
     */
    @ApiModelProperty(value = "运行模式")
    private Byte runningMode;
    /**
     * 来源
     */
    @ApiModelProperty(value = "来源")
    private String source;
    /**
     * 运行状态
     */
    @ApiModelProperty(value = "运行状态")
    private String runningState;
    /**
     * 库名称
     */
    @ApiModelProperty(value = "库名称")
    private String libName;
    /**
     * 包名称
     */
    @ApiModelProperty(value = "包名称")
    private String packageName;
    /**
     * 类名称
     */
    @ApiModelProperty(value = "类名称")
    private String className;
    /**
     * 资源列表
     */
    @ApiModelProperty(value = "资源列表")
    private List<PluginResSaveParameter> resList;
    /**
     * 依赖列表
     */
    @ApiModelProperty(value = "依赖列表")
    private List<PluginDependencySaveParameter> dependencies;
}
