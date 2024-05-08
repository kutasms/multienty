package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.PluginPriceStrategy;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginPriceStrategyDTO;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyEnableParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyDisableParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategySaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginPriceStrategyUpdateParameter;
/**
 * <p>
 * 插件价格策略 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
public interface PluginPriceStrategyService extends KutaBaseService<PluginPriceStrategy> {

    PluginPriceStrategyDTO getDetail(PluginPriceStrategyDetailGetParameter parameter);

    void delete(PluginPriceStrategyDeleteParameter parameter);

    IPage<PluginPriceStrategyDTO> getPage(PluginPriceStrategyPageGetParameter parameter);
    void enable(PluginPriceStrategyEnableParameter parameter);

    void disable(PluginPriceStrategyDisableParameter parameter);

    void save(PluginPriceStrategySaveParameter parameter);

    void update(PluginPriceStrategyUpdateParameter parameter);

}
