package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.OauthAccessToken;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.OauthAccessTokenDTO;
import com.chia.multienty.core.parameter.oauth.OauthAccessTokenDetailGetParameter;
import com.chia.multienty.core.parameter.oauth.OauthAccessTokenPageGetParameter;
import com.chia.multienty.core.parameter.oauth.OauthAccessTokenDeleteParameter;
import com.chia.multienty.core.parameter.oauth.OauthAccessTokenSaveParameter;
import com.chia.multienty.core.parameter.oauth.OauthAccessTokenUpdateParameter;
/**
 * <p>
 * 访问令牌 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
public interface OauthAccessTokenService extends KutaBaseService<OauthAccessToken> {

    OauthAccessTokenDTO getDetail(OauthAccessTokenDetailGetParameter parameter);

    void delete(OauthAccessTokenDeleteParameter parameter);

    IPage<OauthAccessTokenDTO> getPage(OauthAccessTokenPageGetParameter parameter);

    void save(OauthAccessTokenSaveParameter parameter);

    void update(OauthAccessTokenUpdateParameter parameter);

}
