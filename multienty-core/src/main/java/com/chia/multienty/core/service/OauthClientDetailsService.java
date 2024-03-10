package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.OauthClientDetails;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.OauthClientDetailsDTO;
import com.chia.multienty.core.parameter.oauth.OauthClientDetailsDetailGetParameter;
import com.chia.multienty.core.parameter.oauth.OauthClientDetailsPageGetParameter;
import com.chia.multienty.core.parameter.oauth.OauthClientDetailsDeleteParameter;
import com.chia.multienty.core.parameter.oauth.OauthClientDetailsSaveParameter;
import com.chia.multienty.core.parameter.oauth.OauthClientDetailsUpdateParameter;
/**
 * <p>
 * 客户端信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
public interface OauthClientDetailsService extends KutaBaseService<OauthClientDetails> {

    OauthClientDetailsDTO getDetail(OauthClientDetailsDetailGetParameter parameter);

    void delete(OauthClientDetailsDeleteParameter parameter);

    IPage<OauthClientDetailsDTO> getPage(OauthClientDetailsPageGetParameter parameter);

    void save(OauthClientDetailsSaveParameter parameter);

    void update(OauthClientDetailsUpdateParameter parameter);

}
