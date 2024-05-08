package com.chia.multienty.core.parameter.plugin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "PluginDetailGetParameter",description = "插件详情获取请求")
public class PluginDetailGetParameter {
    /**
     * 插件编号
     */
     @ApiModelProperty(value = "插件编号")
     private Long pluginId;
    /**
     * 是否包含插件资源
     */
    @ApiModelProperty(value = "是否包含插件资源")
     private Boolean containsRes = false;
    /**
     * 是否包含插件依赖
     */
    @ApiModelProperty(value = "是否包含插件依赖")
     private Boolean containsDependency = false;
}
