package com.chia.multienty.core.strategy.sms;


import com.aliyuncs.exceptions.ClientException;
import com.chia.multienty.core.strategy.sms.domain.GenericContent;
import com.chia.multienty.core.strategy.sms.domain.SMSResult;
import com.chia.multienty.core.strategy.sms.domain.VerificationCodeBO;

public interface SMSService {

    SMSResult send(GenericContent content);

    SMSResult sendVerificationCode(VerificationCodeBO verificationCodeBO) throws ClientException;
}
