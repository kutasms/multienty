package com.chia.multienty.core.parameter.plugin;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginEnableParameter",description = "插件启用请求")
@Accessors(chain = true)
public class PluginEnableParameter {
    /**
     * 插件编号
     */
     @ApiModelProperty(value = "插件编号")
     @LogMetaId
     private Long pluginId;
}
