package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.AppInstanceDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.AppInstanceMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.pojo.AppInstance;
import com.chia.multienty.core.service.AppInstanceService;
import com.chia.multienty.core.tools.IdWorkerProvider;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 应用实例 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@Service
@RequiredArgsConstructor
public class AppInstanceServiceImpl extends KutaBaseServiceImpl<AppInstanceMapper, AppInstance> implements AppInstanceService {


    @Override
    public AppInstanceDTO getDetail(AppInstanceDetailGetParameter parameter) {
        return selectJoinOne(AppInstanceDTO.class,
                        MPJWrappers.<AppInstance>lambdaJoin().eq(AppInstance::getInstanceId, parameter.getInstanceId()));
    }

    @Override
    public void update(AppInstanceUpdateParameter parameter) {
        AppInstance appInstance = new AppInstance();
        BeanUtils.copyProperties(parameter, appInstance);
        updateByIdTE(appInstance);
    }

    @Override
    public void delete(AppInstanceDeleteParameter parameter) {
        removeByIdTE(parameter.getInstanceId());
    }

    @Override
    public IPage<AppInstanceDTO> getPage(AppInstancePageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), AppInstanceDTO.class,
                new MTLambdaWrapper<AppInstance>()
                        .solveGenericParameters(parameter)
                        .eq(parameter.getTenantId()!=null, AppInstance::getTenantId, parameter.getTenantId())
                        .eq(Strings.hasText(parameter.getKeywords()), AppInstance::getAlias, parameter.getKeywords())
                        .eq(parameter.getAppId()!=null, AppInstance::getAppId, parameter.getAppId())
                        .in(!ListUtil.isEmpty(parameter.getInstanceIds()),
                                AppInstance::getInstanceId,
                                parameter.getInstanceIds())
        );
    }

    @Override
    public void save(AppInstanceSaveParameter parameter) {
        AppInstance appInstance = new AppInstance();
        BeanUtils.copyProperties(parameter, appInstance);
        appInstance.setInstanceId(IdWorkerProvider.next());
        saveTE(appInstance);
        parameter.setInstanceId(appInstance.getInstanceId());
    }


    @Override
    public void enable(AppInstanceEnableParameter parameter) {
        AppInstance appInstance = new AppInstance();
        BeanUtils.copyProperties(parameter, appInstance);
        appInstance.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(appInstance);

    }

    @Override
    public void disable(AppInstanceDisableParameter parameter) {
        AppInstance appInstance = new AppInstance();
        BeanUtils.copyProperties(parameter, appInstance);
        appInstance.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(appInstance);
    }
}
