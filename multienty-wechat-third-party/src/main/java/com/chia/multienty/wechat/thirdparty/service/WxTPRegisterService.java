package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.define.register.EnterpriseVerifyInfo;
import com.chia.multienty.wechat.thirdparty.parameter.register.BetaMiniProgramRegisterParameter;
import com.chia.multienty.wechat.thirdparty.parameter.register.EnterpriseMiniProgramRegisterParameter;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.*;

public interface WxTPRegisterService {
    BetaMiniProgramNickNameModifyResponse modifyBetaMiniProgramNickName(String appId, String name);


    BetaMiniProgramRegisterResponse registerBetaMiniProgram(BetaMiniProgramRegisterParameter parameter);

    BetaMiniProgramVerifyResponse verifyBetaMiniProgram(String appId, EnterpriseVerifyInfo verifyInfo);

    EnterpriseMiniProgramRegisterResponse registerEnterpriseMiniProgram(EnterpriseMiniProgramRegisterParameter parameter);

    MiniProgramRegisterByOfficialAccountResponse registerMiniProgramByOfficialAccount(String appId, String ticket);
}
