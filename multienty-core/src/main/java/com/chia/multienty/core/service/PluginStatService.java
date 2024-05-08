package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.PluginStat;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginStatDTO;
import com.chia.multienty.core.parameter.plugin.PluginStatDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginStatPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginStatDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginStatSaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginStatUpdateParameter;
/**
 * <p>
 * 插件统计 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
public interface PluginStatService extends KutaBaseService<PluginStat> {

    PluginStatDTO getDetail(PluginStatDetailGetParameter parameter);

    void delete(PluginStatDeleteParameter parameter);

    IPage<PluginStatDTO> getPage(PluginStatPageGetParameter parameter);

    void save(PluginStatSaveParameter parameter);

    void update(PluginStatUpdateParameter parameter);

}
