package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.PluginDependency;
import com.chia.multienty.core.mapper.PluginDependencyMapper;
import com.chia.multienty.core.service.PluginDependencyService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginDependencyDTO;
import com.chia.multienty.core.parameter.plugin.PluginDependencyDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginDependencyPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginDependencyDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginDependencySaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginDependencyUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chia.multienty.core.tools.IdWorkerProvider;
/**
 * <p>
 * 插件依赖 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */
@Service
@RequiredArgsConstructor
public class PluginDependencyServiceImpl extends KutaBaseServiceImpl<PluginDependencyMapper, PluginDependency> implements PluginDependencyService {


    @Override
    public PluginDependencyDTO getDetail(PluginDependencyDetailGetParameter parameter) {
        return selectJoinOne(PluginDependencyDTO.class,
                        MPJWrappers.<PluginDependency>lambdaJoin().eq(PluginDependency::getDependencyId, parameter.getDependencyId()));
    }

    @Override
    public void update(PluginDependencyUpdateParameter parameter) {
        PluginDependency pluginDependency = new PluginDependency();
        BeanUtils.copyProperties(parameter, pluginDependency);
        updateByIdTE(pluginDependency);
    }

    @Override
    public void delete(PluginDependencyDeleteParameter parameter) {
        removeByIdTE(parameter.getDependencyId());
    }

    @Override
    public IPage<PluginDependencyDTO> getPage(PluginDependencyPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), PluginDependencyDTO.class,
                new MTLambdaWrapper<PluginDependency>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getDependencyIds()),
                                PluginDependency::getDependencyId,
                                parameter.getDependencyIds())
        );
    }

    @Override
    public void save(PluginDependencySaveParameter parameter) {
        PluginDependency pluginDependency = new PluginDependency();
        BeanUtils.copyProperties(parameter, pluginDependency);
        pluginDependency.setDependencyId(IdWorkerProvider.next());
        saveTE(pluginDependency);
        parameter.setDependencyId(pluginDependency.getDependencyId());
    }


}
