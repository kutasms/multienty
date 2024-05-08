package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginDTO;
import com.chia.multienty.core.domain.dto.PluginResDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.PluginMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.plugin.*;
import com.chia.multienty.core.pojo.Plugin;
import com.chia.multienty.core.pojo.PluginDependency;
import com.chia.multienty.core.pojo.PluginRes;
import com.chia.multienty.core.service.PluginDependencyService;
import com.chia.multienty.core.service.PluginResService;
import com.chia.multienty.core.service.PluginService;
import com.chia.multienty.core.tools.IdWorkerProvider;
import com.chia.multienty.core.util.AssertUtil;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 插件 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@Service
@RequiredArgsConstructor
public class PluginServiceImpl extends KutaBaseServiceImpl<PluginMapper, Plugin> implements PluginService {

    private final PluginResService pluginResService;

    private final PluginDependencyService pluginDependencyService;

    @Override
    public PluginDTO getDetail(PluginDetailGetParameter parameter) {
        PluginDTO pluginDTO = selectJoinOne(PluginDTO.class,
                MPJWrappers.<Plugin>lambdaJoin().eq(Plugin::getPluginId, parameter.getPluginId()));
        if(parameter.getContainsDependency()) {
            List<PluginDependency> dependencies = pluginDependencyService.dtoListBy(PluginDependency.class,
                    consumer -> consumer.eq(PluginDependency::getPluginId,
                            parameter.getPluginId()));
            pluginDTO.setDependencies(dependencies);
        }
        if(parameter.getContainsRes()) {
            List<PluginResDTO> resList = pluginResService.dtoListBy(PluginResDTO.class,
                    consumer -> consumer.eq(PluginRes::getPluginId, parameter.getPluginId()));
            pluginDTO.setResList(resList);
        }
        return pluginDTO;
    }

    @Override
    public void update(PluginUpdateParameter parameter) {
        Plugin plugin = new Plugin();
        BeanUtils.copyProperties(parameter, plugin);
        updateByIdTE(plugin);
    }

    @Override
    public void delete(PluginDeleteParameter parameter) {
        removeByIdTE(parameter.getPluginId());
    }

    @Override
    public IPage<PluginDTO> getPage(PluginPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), PluginDTO.class,
                new MTLambdaWrapper<Plugin>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getPluginIds()),
                                Plugin::getPluginId,
                                parameter.getPluginIds())
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(PluginSaveParameter parameter) {
        AssertUtil.checkNull(parameter.getResList(), "plugin resources");
        
        Plugin plugin = new Plugin();
        BeanUtils.copyProperties(parameter, plugin);
        plugin.setPluginId(IdWorkerProvider.next());
        saveTE(plugin);
        parameter.setPluginId(plugin.getPluginId());

    }


    @Override
    public void enable(PluginEnableParameter parameter) {
        Plugin plugin = new Plugin();
        BeanUtils.copyProperties(parameter, plugin);
        plugin.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(plugin);

    }

    @Override
    public void disable(PluginDisableParameter parameter) {
        Plugin plugin = new Plugin();
        BeanUtils.copyProperties(parameter, plugin);
        plugin.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(plugin);
    }
}
