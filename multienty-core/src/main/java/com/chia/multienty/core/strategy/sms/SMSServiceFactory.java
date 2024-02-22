package com.chia.multienty.core.strategy.sms;

import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.core.strategy.sms.aliyun.AliyunService;

public class SMSServiceFactory {

    public static SMSService getService()  {
        return SpringUtil.getBean( AliyunService.class);
    }

}
