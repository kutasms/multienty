package com.chia.multienty.core.dubbo.service;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.vo.LoginResult;
import com.chia.multienty.core.parameter.user.LoginParameter;
import com.chia.multienty.core.service.MultientyUserService;

public interface DubboWxOAuthService extends MultientyUserService {
    Result<LoginResult> login(LoginParameter parameter) throws Exception;
}
