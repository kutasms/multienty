package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.OauthAccessTokenDTO;
import com.chia.multienty.core.mapper.OauthAccessTokenMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.oauth.*;
import com.chia.multienty.core.pojo.OauthAccessToken;
import com.chia.multienty.core.service.OauthAccessTokenService;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 访问令牌 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
@Service
@RequiredArgsConstructor
public class OauthAccessTokenServiceImpl extends KutaBaseServiceImpl<OauthAccessTokenMapper, OauthAccessToken> implements OauthAccessTokenService {


    @Override
    public OauthAccessTokenDTO getDetail(OauthAccessTokenDetailGetParameter parameter) {
        return selectJoinOne(OauthAccessTokenDTO.class,
                        MPJWrappers.<OauthAccessToken>lambdaJoin().eq(OauthAccessToken::getAuthenticationId, parameter.getAuthenticationId()));
    }

    @Override
    public void update(OauthAccessTokenUpdateParameter parameter) {
        OauthAccessToken oauthAccessToken = new OauthAccessToken();
        BeanUtils.copyProperties(parameter, oauthAccessToken);
        updateByIdTE(oauthAccessToken);
    }

    @Override
    public void delete(OauthAccessTokenDeleteParameter parameter) {
        removeByIdTE(parameter.getAuthenticationId());
    }

    @Override
    public IPage<OauthAccessTokenDTO> getPage(OauthAccessTokenPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), OauthAccessTokenDTO.class,
                new MTLambdaWrapper<OauthAccessToken>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getAuthenticationIds()),
                                OauthAccessToken::getAuthenticationId,
                                parameter.getAuthenticationIds())
        );
    }

    @Override
    public void save(OauthAccessTokenSaveParameter parameter) {
        OauthAccessToken oauthAccessToken = new OauthAccessToken();
        BeanUtils.copyProperties(parameter, oauthAccessToken);
        saveTE(oauthAccessToken);
        parameter.setAuthenticationId(oauthAccessToken.getAuthenticationId());
    }


}
