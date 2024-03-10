package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.OauthClientDetailsDTO;
import com.chia.multienty.core.mapper.OauthClientDetailsMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.oauth.*;
import com.chia.multienty.core.pojo.OauthClientDetails;
import com.chia.multienty.core.service.OauthClientDetailsService;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 客户端信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
@Service
@RequiredArgsConstructor
public class OauthClientDetailsServiceImpl extends KutaBaseServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsService {


    @Override
    public OauthClientDetailsDTO getDetail(OauthClientDetailsDetailGetParameter parameter) {
        return selectJoinOne(OauthClientDetailsDTO.class,
                        MPJWrappers.<OauthClientDetails>lambdaJoin().eq(OauthClientDetails::getClientId, parameter.getClientId()));
    }

    @Override
    public void update(OauthClientDetailsUpdateParameter parameter) {
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        BeanUtils.copyProperties(parameter, oauthClientDetails);
        updateByIdTE(oauthClientDetails);
    }

    @Override
    public void delete(OauthClientDetailsDeleteParameter parameter) {
        removeByIdTE(parameter.getClientId());
    }

    @Override
    public IPage<OauthClientDetailsDTO> getPage(OauthClientDetailsPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), OauthClientDetailsDTO.class,
                new MTLambdaWrapper<OauthClientDetails>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getClientIds()),
                                OauthClientDetails::getClientId,
                                parameter.getClientIds())
        );
    }

    @Override
    public void save(OauthClientDetailsSaveParameter parameter) {
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        BeanUtils.copyProperties(parameter, oauthClientDetails);
        saveTE(oauthClientDetails);
        parameter.setClientId(oauthClientDetails.getClientId());
    }


}
