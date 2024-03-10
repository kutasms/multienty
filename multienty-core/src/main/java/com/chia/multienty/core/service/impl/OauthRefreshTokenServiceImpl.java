package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.mapper.OauthRefreshTokenMapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.oauth.OauthRefreshTokenSaveParameter;
import com.chia.multienty.core.parameter.oauth.OauthRefreshTokenUpdateParameter;
import com.chia.multienty.core.pojo.OauthRefreshToken;
import com.chia.multienty.core.service.OauthRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 更新令牌 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
@Service
@RequiredArgsConstructor
public class OauthRefreshTokenServiceImpl extends KutaBaseServiceImpl<OauthRefreshTokenMapper, OauthRefreshToken> implements OauthRefreshTokenService {


    @Override
    public void save(OauthRefreshTokenSaveParameter parameter) {
        OauthRefreshToken oauthRefreshToken = new OauthRefreshToken();
        BeanUtils.copyProperties(parameter, oauthRefreshToken);
        saveTE(oauthRefreshToken);
    }

    @Override
    public void update(OauthRefreshTokenUpdateParameter parameter) {

    }


}
