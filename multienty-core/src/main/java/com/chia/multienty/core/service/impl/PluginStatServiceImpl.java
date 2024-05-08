package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.PluginStat;
import com.chia.multienty.core.mapper.PluginStatMapper;
import com.chia.multienty.core.service.PluginStatService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginStatDTO;
import com.chia.multienty.core.parameter.plugin.PluginStatDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginStatPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginStatDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginStatSaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginStatUpdateParameter;
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
 * 插件统计 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@Service
@RequiredArgsConstructor
public class PluginStatServiceImpl extends KutaBaseServiceImpl<PluginStatMapper, PluginStat> implements PluginStatService {


    @Override
    public PluginStatDTO getDetail(PluginStatDetailGetParameter parameter) {
        return selectJoinOne(PluginStatDTO.class,
                        MPJWrappers.<PluginStat>lambdaJoin().eq(PluginStat::getPluginId, parameter.getPluginId()));
    }

    @Override
    public void update(PluginStatUpdateParameter parameter) {
        PluginStat pluginStat = new PluginStat();
        BeanUtils.copyProperties(parameter, pluginStat);
        updateByIdTE(pluginStat);
    }

    @Override
    public void delete(PluginStatDeleteParameter parameter) {
        removeByIdTE(parameter.getPluginId());
    }

    @Override
    public IPage<PluginStatDTO> getPage(PluginStatPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), PluginStatDTO.class,
                new MTLambdaWrapper<PluginStat>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getPluginIds()),
                                PluginStat::getPluginId,
                                parameter.getPluginIds())
        );
    }

    @Override
    public void save(PluginStatSaveParameter parameter) {
        PluginStat pluginStat = new PluginStat();
        BeanUtils.copyProperties(parameter, pluginStat);
        pluginStat.setPluginId(IdWorkerProvider.next());
        saveTE(pluginStat);
        parameter.setPluginId(pluginStat.getPluginId());
    }


}
