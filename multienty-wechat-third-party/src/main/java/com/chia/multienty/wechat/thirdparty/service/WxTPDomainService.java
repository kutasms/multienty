package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.parameter.domain.ServerDomainModifyDirectlyParameter;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ServerDomainModifyParameter;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ThirdPartyJumpDomainModifyParameter;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ThirdPartyServerDomainModifyParameter;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.*;

import java.util.List;

public interface WxTPDomainService {
    EffectiveJumpDomainGetResponse getEffectiveJumpDomain();

    EffectiveServerDomainGetResponse getEffectiveServerDomain();

    JumpDomainConfirmFileGetResponse getJumpDomainConfirmFile();

    JumpDomainModifyDirectlyResponse modifyJumpDomainDirectly(String action, List<String> webViewDomains);

    JumpDomainModifyResponse modifyJumpDomain(String action, List<String> webViewDomains);

    ServerDomainModifyDirectlyResponse modifyServerDomainDirectly(ServerDomainModifyDirectlyParameter parameter);

    ServerDomainModifyResponse modifyServerDomain(ServerDomainModifyParameter parameter);

    ThirdPartyJumpDomainConfirmFileGetResponse getThirdPartyJumpDomainConfirmFile();

    ThirdPartyJumpDomainModifyResponse modifyThirdPartyJumpDomain(ThirdPartyJumpDomainModifyParameter parameter);

    ThirdPartyServerDomainModifyResponse modifyThirdPartyServerDomain(ThirdPartyServerDomainModifyParameter parameter);
}
