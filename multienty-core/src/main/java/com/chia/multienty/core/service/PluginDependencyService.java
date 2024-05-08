package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.PluginDependency;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginDependencyDTO;
import com.chia.multienty.core.parameter.plugin.PluginDependencyDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginDependencyPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginDependencyDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginDependencySaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginDependencyUpdateParameter;
/**
 * <p>
 * 插件依赖 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */
public interface PluginDependencyService extends KutaBaseService<PluginDependency> {

    PluginDependencyDTO getDetail(PluginDependencyDetailGetParameter parameter);

    void delete(PluginDependencyDeleteParameter parameter);

    IPage<PluginDependencyDTO> getPage(PluginDependencyPageGetParameter parameter);

    void save(PluginDependencySaveParameter parameter);

    void update(PluginDependencyUpdateParameter parameter);

}
