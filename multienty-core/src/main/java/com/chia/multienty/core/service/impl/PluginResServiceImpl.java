package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.PluginRes;
import com.chia.multienty.core.mapper.PluginResMapper;
import com.chia.multienty.core.service.PluginResService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PluginResDTO;
import com.chia.multienty.core.parameter.plugin.PluginResDetailGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginResPageGetParameter;
import com.chia.multienty.core.parameter.plugin.PluginResDeleteParameter;
import com.chia.multienty.core.parameter.plugin.PluginResSaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginResUpdateParameter;
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
 * 插件资源 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */
@Service
@RequiredArgsConstructor
public class PluginResServiceImpl extends KutaBaseServiceImpl<PluginResMapper, PluginRes> implements PluginResService {


    @Override
    public PluginResDTO getDetail(PluginResDetailGetParameter parameter) {
        return selectJoinOne(PluginResDTO.class,
                        MPJWrappers.<PluginRes>lambdaJoin().eq(PluginRes::getResId, parameter.getResId()));
    }

    @Override
    public void update(PluginResUpdateParameter parameter) {
        PluginRes pluginRes = new PluginRes();
        BeanUtils.copyProperties(parameter, pluginRes);
        updateByIdTE(pluginRes);
    }

    @Override
    public void delete(PluginResDeleteParameter parameter) {
        removeByIdTE(parameter.getResId());
    }

    @Override
    public IPage<PluginResDTO> getPage(PluginResPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), PluginResDTO.class,
                new MTLambdaWrapper<PluginRes>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getResIds()),
                                PluginRes::getResId,
                                parameter.getResIds())
        );
    }

    @Override
    public void save(PluginResSaveParameter parameter) {
        PluginRes pluginRes = new PluginRes();
        BeanUtils.copyProperties(parameter, pluginRes);
        pluginRes.setResId(IdWorkerProvider.next());
        saveTE(pluginRes);
        parameter.setResId(pluginRes.getResId());
    }


}
