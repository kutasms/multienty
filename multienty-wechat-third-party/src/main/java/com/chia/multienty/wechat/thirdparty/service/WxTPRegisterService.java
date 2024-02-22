package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.define.register.EnterpriseVerifyInfo;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.*;
import com.chia.multienty.wechat.thirdparty.parameter.register.EnterpriseMiniProgramRegisterParameter;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.*;

public interface WxTPRegisterService {
    BetaMiniProgramNickNameModifyResponse modifyBetaMiniProgramNickName(String appId, String name);

    BetaMiniProgramRegisterResponse registerBetaMiniProgram(String name, String openId);

    BetaMiniProgramVerifyResponse verifyBetaMiniProgram(String appId, EnterpriseVerifyInfo verifyInfo);

    EnterpriseMiniProgramRegisterResponse registerEnterpriseMiniProgram(EnterpriseMiniProgramRegisterParameter parameter);

    MiniProgramRegisterByOfficialAccountResponse registerMiniProgramByOfficialAccount(String appId, String ticket);
}
