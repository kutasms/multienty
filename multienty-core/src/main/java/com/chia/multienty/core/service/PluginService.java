package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.Plugin;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginDTO;
import com.chia.multienty.core.parameter.plugin.PluginDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginEnableParameter;
import com.chia.multienty.core.parameter.plugin.PluginDisableParameter;
import com.chia.multienty.core.parameter.plugin.PluginSaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginUpdateParameter;
/**
 * <p>
 * 插件 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
public interface PluginService extends KutaBaseService<Plugin> {

    PluginDTO getDetail(PluginDetailGetParameter parameter);

    void delete(PluginDeleteParameter parameter);

    IPage<PluginDTO> getPage(PluginPageGetParameter parameter);
    void enable(PluginEnableParameter parameter);

    void disable(PluginDisableParameter parameter);

    void save(PluginSaveParameter parameter);

    void update(PluginUpdateParameter parameter);

}
