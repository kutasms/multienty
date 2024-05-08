package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.PluginDependency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 插件依赖 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PluginDependencyDTO", description="插件依赖DTO对象")
public class PluginDependencyDTO extends PluginDependency {
}
