package com.chia.multienty.core.parameter.plugin;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件依赖更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */

@Data
@ApiModel(value = "PluginDependencyUpdateParameter",description = "插件依赖更新请求")
@Accessors(chain = true)
public class PluginDependencyUpdateParameter {

    /**
     * 插件依赖编号
     */
     @ApiModelProperty(value = "插件依赖编号")
     @LogMetaId
     private Long dependencyId;
    /**
     * 插件编号
     */
     @ApiModelProperty(value = "插件编号")
     private Long pluginId;
    /**
     * 依赖资源名
     */
     @ApiModelProperty(value = "依赖资源名")
     private Integer depResName;
    /**
     * 文件扩展名
     */
     @ApiModelProperty(value = "文件扩展名")
     private Integer extName;
    /**
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
}
