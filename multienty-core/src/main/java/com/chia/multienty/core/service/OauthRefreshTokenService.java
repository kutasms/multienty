package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.OauthRefreshToken;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.OauthRefreshTokenDTO;
import com.chia.multienty.core.parameter.oauth.OauthRefreshTokenDetailGetParameter;
import com.chia.multienty.core.parameter.oauth.OauthRefreshTokenPageGetParameter;
import com.chia.multienty.core.parameter.oauth.OauthRefreshTokenDeleteParameter;
import com.chia.multienty.core.parameter.oauth.OauthRefreshTokenSaveParameter;
import com.chia.multienty.core.parameter.oauth.OauthRefreshTokenUpdateParameter;
/**
 * <p>
 * 更新令牌 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
public interface OauthRefreshTokenService extends KutaBaseService<OauthRefreshToken> {


    void save(OauthRefreshTokenSaveParameter parameter);

    void update(OauthRefreshTokenUpdateParameter parameter);

}
