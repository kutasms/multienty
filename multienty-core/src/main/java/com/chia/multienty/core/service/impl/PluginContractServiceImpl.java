package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.PluginContract;
import com.chia.multienty.core.mapper.PluginContractMapper;
import com.chia.multienty.core.service.PluginContractService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginContractDTO;
import com.chia.multienty.core.parameter.plugin.PluginContractDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractSaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractUpdateParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractEnableParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractDisableParameter;
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
 * 插件合约 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@Service
@RequiredArgsConstructor
public class PluginContractServiceImpl extends KutaBaseServiceImpl<PluginContractMapper, PluginContract> implements PluginContractService {


    @Override
    public PluginContractDTO getDetail(PluginContractDetailGetParameter parameter) {
        return selectJoinOne(PluginContractDTO.class,
                        MPJWrappers.<PluginContract>lambdaJoin().eq(PluginContract::getContractId, parameter.getContractId()));
    }

    @Override
    public void update(PluginContractUpdateParameter parameter) {
        PluginContract pluginContract = new PluginContract();
        BeanUtils.copyProperties(parameter, pluginContract);
        updateByIdTE(pluginContract);
    }

    @Override
    public void delete(PluginContractDeleteParameter parameter) {
        removeByIdTE(parameter.getContractId());
    }

    @Override
    public IPage<PluginContractDTO> getPage(PluginContractPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), PluginContractDTO.class,
                new MTLambdaWrapper<PluginContract>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getContractIds()),
                                PluginContract::getContractId,
                                parameter.getContractIds())
        );
    }

    @Override
    public void save(PluginContractSaveParameter parameter) {
        PluginContract pluginContract = new PluginContract();
        BeanUtils.copyProperties(parameter, pluginContract);
        pluginContract.setContractId(IdWorkerProvider.next());
        saveTE(pluginContract);
        parameter.setContractId(pluginContract.getContractId());
    }


    @Override
    public void enable(PluginContractEnableParameter parameter) {
        PluginContract pluginContract = new PluginContract();
        BeanUtils.copyProperties(parameter, pluginContract);
        pluginContract.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(pluginContract);

    }

    @Override
    public void disable(PluginContractDisableParameter parameter) {
        PluginContract pluginContract = new PluginContract();
        BeanUtils.copyProperties(parameter, pluginContract);
        pluginContract.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(pluginContract);
    }
}
