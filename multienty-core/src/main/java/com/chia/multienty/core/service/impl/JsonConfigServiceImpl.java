package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.dto.JsonConfigDTO;
import com.chia.multienty.core.mapper.JsonConfigMapper;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.JsonConfig;
import com.chia.multienty.core.service.JsonConfigService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.JsonConfigDetailGetParameter;
import com.chia.multienty.core.parameter.base.JsonConfigPageGetParameter;
import com.chia.multienty.core.parameter.base.JsonConfigDeleteParameter;
import com.chia.multienty.core.parameter.base.JsonConfigSaveParameter;
import com.chia.multienty.core.parameter.base.JsonConfigUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * Json格式配置信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
@Service
public class JsonConfigServiceImpl extends KutaBaseServiceImpl<JsonConfigMapper, JsonConfig> implements JsonConfigService {


    @Override
    public JsonConfigDTO getDetail(JsonConfigDetailGetParameter parameter) {
        return selectJoinOne(JsonConfigDTO.class,
                        MPJWrappers.<JsonConfig>lambdaJoin().eq(JsonConfig::getConfigId, parameter.getConfigId()));
    }

    @Override
    public void delete(JsonConfigDeleteParameter parameter) {
        removeByIdTE(parameter.getConfigId());
    }

    @Override
    public IPage<JsonConfigDTO> getPage(JsonConfigPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), JsonConfigDTO.class,
                new KutaLambdaWrapper<JsonConfig>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getConfigIds()), JsonConfig::getConfigId, parameter.getConfigIds())
        );
    }

    @Override
    public void save(JsonConfigSaveParameter parameter) {
        JsonConfig jsonConfig = new JsonConfig();
        BeanUtils.copyProperties(parameter, jsonConfig);
        saveTE(jsonConfig);
        parameter.setConfigId(jsonConfig.getConfigId());
    }

    @Override
    public void update(JsonConfigUpdateParameter parameter) {
        JsonConfig jsonConfig = new JsonConfig();
        BeanUtils.copyProperties(parameter, jsonConfig);
        updateByIdTE(jsonConfig);
    }
}
