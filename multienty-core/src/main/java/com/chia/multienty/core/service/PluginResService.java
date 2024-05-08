package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.PluginRes;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginResDTO;
import com.chia.multienty.core.parameter.plugin.PluginResDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginResPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginResDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginResSaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginResUpdateParameter;
/**
 * <p>
 * 插件资源 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */
public interface PluginResService extends KutaBaseService<PluginRes> {

    PluginResDTO getDetail(PluginResDetailGetParameter parameter);

    void delete(PluginResDeleteParameter parameter);

    IPage<PluginResDTO> getPage(PluginResPageGetParameter parameter);

    void save(PluginResSaveParameter parameter);

    void update(PluginResUpdateParameter parameter);

}
