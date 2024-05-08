package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.PluginContract;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginContractDTO;
import com.chia.multienty.core.parameter.plugin.PluginContractDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractEnableParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractDisableParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractSaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginContractUpdateParameter;
/**
 * <p>
 * 插件合约 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
public interface PluginContractService extends KutaBaseService<PluginContract> {

    PluginContractDTO getDetail(PluginContractDetailGetParameter parameter);

    void delete(PluginContractDeleteParameter parameter);

    IPage<PluginContractDTO> getPage(PluginContractPageGetParameter parameter);
    void enable(PluginContractEnableParameter parameter);

    void disable(PluginContractDisableParameter parameter);

    void save(PluginContractSaveParameter parameter);

    void update(PluginContractUpdateParameter parameter);

}
