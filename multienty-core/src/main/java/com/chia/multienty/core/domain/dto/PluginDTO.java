package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.Plugin;
import com.chia.multienty.core.pojo.PluginDependency;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 插件 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PluginDTO", description="插件DTO对象")
public class PluginDTO extends Plugin {
    /**
     * 资源列表
     */
    private List<PluginResDTO> resList;
    /**
     * 依赖列表
     */
    private List<PluginDependency> dependencies;
}
