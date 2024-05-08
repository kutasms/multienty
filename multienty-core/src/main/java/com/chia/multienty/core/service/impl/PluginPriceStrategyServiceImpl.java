package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.PluginPriceStrategy;
import com.chia.multienty.core.mapper.PluginPriceStrategyMapper;
import com.chia.multienty.core.service.PluginPriceStrategyService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginPriceStrategyDTO;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategySaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyUpdateParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyEnableParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyDisableParameter;
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
 * 插件价格策略 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@Service
@RequiredArgsConstructor
public class PluginPriceStrategyServiceImpl extends KutaBaseServiceImpl<PluginPriceStrategyMapper, PluginPriceStrategy> implements PluginPriceStrategyService {


    @Override
    public PluginPriceStrategyDTO getDetail(PluginPriceStrategyDetailGetParameter parameter) {
        return selectJoinOne(PluginPriceStrategyDTO.class,
                        MPJWrappers.<PluginPriceStrategy>lambdaJoin().eq(PluginPriceStrategy::getStrategyId, parameter.getStrategyId()));
    }

    @Override
    public void update(PluginPriceStrategyUpdateParameter parameter) {
        PluginPriceStrategy pluginPriceStrategy = new PluginPriceStrategy();
        BeanUtils.copyProperties(parameter, pluginPriceStrategy);
        updateByIdTE(pluginPriceStrategy);
    }

    @Override
    public void delete(PluginPriceStrategyDeleteParameter parameter) {
        removeByIdTE(parameter.getStrategyId());
    }

    @Override
    public IPage<PluginPriceStrategyDTO> getPage(PluginPriceStrategyPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), PluginPriceStrategyDTO.class,
                new MTLambdaWrapper<PluginPriceStrategy>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getStrategyIds()),
                                PluginPriceStrategy::getStrategyId,
                                parameter.getStrategyIds())
        );
    }

    @Override
    public void save(PluginPriceStrategySaveParameter parameter) {
        PluginPriceStrategy pluginPriceStrategy = new PluginPriceStrategy();
        BeanUtils.copyProperties(parameter, pluginPriceStrategy);
        pluginPriceStrategy.setStrategyId(IdWorkerProvider.next());
        saveTE(pluginPriceStrategy);
        parameter.setStrategyId(pluginPriceStrategy.getStrategyId());
    }


    @Override
    public void enable(PluginPriceStrategyEnableParameter parameter) {
        PluginPriceStrategy pluginPriceStrategy = new PluginPriceStrategy();
        BeanUtils.copyProperties(parameter, pluginPriceStrategy);
        pluginPriceStrategy.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(pluginPriceStrategy);

    }

    @Override
    public void disable(PluginPriceStrategyDisableParameter parameter) {
        PluginPriceStrategy pluginPriceStrategy = new PluginPriceStrategy();
        BeanUtils.copyProperties(parameter, pluginPriceStrategy);
        pluginPriceStrategy.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(pluginPriceStrategy);
    }
}
