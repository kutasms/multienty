package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.TenantSecretDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.TenantSecretMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.pojo.TenantSecret;
import com.chia.multienty.core.service.TenantSecretService;
import com.chia.multienty.core.tools.IdWorkerProvider;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.core.util.RandomStringUtils;
import com.github.yulichang.toolkit.MPJWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 租户密钥 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@Service
@RequiredArgsConstructor
public class TenantSecretServiceImpl extends KutaBaseServiceImpl<TenantSecretMapper, TenantSecret> implements TenantSecretService {


    @Override
    public TenantSecretDTO getDetail(TenantSecretDetailGetParameter parameter) {
        return selectJoinOne(TenantSecretDTO.class,
                        MPJWrappers.<TenantSecret>lambdaJoin().eq(TenantSecret::getSecretId, parameter.getSecretId()));
    }

    @Override
    public void update(TenantSecretUpdateParameter parameter) {
        TenantSecret tenantSecret = new TenantSecret();
        BeanUtils.copyProperties(parameter, tenantSecret);
        updateByIdTE(tenantSecret);
    }

    @Override
    public void delete(TenantSecretDeleteParameter parameter) {
        removeByIdTE(parameter.getSecretId());
    }

    @Override
    public IPage<TenantSecretDTO> getPage(TenantSecretPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), TenantSecretDTO.class,
                new MTLambdaWrapper<TenantSecret>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getSecretIds()),
                                TenantSecret::getSecretId,
                                parameter.getSecretIds())
        );
    }

    @Override
    public void save(TenantSecretSaveParameter parameter) {
        TenantSecret tenantSecret = new TenantSecret();
        BeanUtils.copyProperties(parameter, tenantSecret);
        tenantSecret.setSecretId(IdWorkerProvider.next());
        tenantSecret.setAppKey(RandomStringUtils.getRandomCode(7, 3));
        tenantSecret.setAppSecret(RandomStringUtils.getRandomCode(32, 3));
        saveTE(tenantSecret);
        parameter.setSecretId(tenantSecret.getSecretId());
    }


    @Override
    public void enable(TenantSecretEnableParameter parameter) {
        TenantSecret tenantSecret = new TenantSecret();
        BeanUtils.copyProperties(parameter, tenantSecret);
        tenantSecret.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(tenantSecret);

    }

    @Override
    public void disable(TenantSecretDisableParameter parameter) {
        TenantSecret tenantSecret = new TenantSecret();
        BeanUtils.copyProperties(parameter, tenantSecret);
        tenantSecret.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(tenantSecret);
    }
}
