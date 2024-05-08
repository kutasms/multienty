package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.SecretAuth;
import com.chia.multienty.core.mapper.SecretAuthMapper;
import com.chia.multienty.core.service.SecretAuthService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.SecretAuthDTO;
import com.chia.multienty.core.parameter.tenant.SecretAuthDetailGetParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthPageGetParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthDeleteParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthSaveParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthUpdateParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthEnableParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthDisableParameter;
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
 * 密钥授权 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@Service
@RequiredArgsConstructor
public class SecretAuthServiceImpl extends KutaBaseServiceImpl<SecretAuthMapper, SecretAuth> implements SecretAuthService {


    @Override
    public SecretAuthDTO getDetail(SecretAuthDetailGetParameter parameter) {
        return selectJoinOne(SecretAuthDTO.class,
                        MPJWrappers.<SecretAuth>lambdaJoin().eq(SecretAuth::getSecAuthId, parameter.getSecAuthId()));
    }

    @Override
    public void update(SecretAuthUpdateParameter parameter) {
        SecretAuth secretAuth = new SecretAuth();
        BeanUtils.copyProperties(parameter, secretAuth);
        updateByIdTE(secretAuth);
    }

    @Override
    public void delete(SecretAuthDeleteParameter parameter) {
        removeByIdTE(parameter.getSecAuthId());
    }

    @Override
    public IPage<SecretAuthDTO> getPage(SecretAuthPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), SecretAuthDTO.class,
                new MTLambdaWrapper<SecretAuth>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getSecAuthIds()),
                                SecretAuth::getSecAuthId,
                                parameter.getSecAuthIds())
        );
    }

    @Override
    public void save(SecretAuthSaveParameter parameter) {
        SecretAuth secretAuth = new SecretAuth();
        BeanUtils.copyProperties(parameter, secretAuth);
        secretAuth.setSecAuthId(IdWorkerProvider.next());
        saveTE(secretAuth);
        parameter.setSecAuthId(secretAuth.getSecAuthId());
    }


    @Override
    public void enable(SecretAuthEnableParameter parameter) {
        SecretAuth secretAuth = new SecretAuth();
        BeanUtils.copyProperties(parameter, secretAuth);
        secretAuth.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(secretAuth);

    }

    @Override
    public void disable(SecretAuthDisableParameter parameter) {
        SecretAuth secretAuth = new SecretAuth();
        BeanUtils.copyProperties(parameter, secretAuth);
        secretAuth.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(secretAuth);
    }
}
