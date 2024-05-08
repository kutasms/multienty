package com.chia.multienty.core.strategy.sms;

import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.strategy.sms.properties.YamlSMSProperties;
import com.chia.multienty.core.util.SpringUtil;

import java.util.Map;
import java.util.Set;

public class SMSServiceFactory {


    public static SMSService getService(String name)  {

        Map<String, SMSService> smsMap = SpringUtil.getApplicationContext().getBeansOfType(SMSService.class);
        if (smsMap == null) {
            throw new KutaRuntimeException(500, "please at least provide one implement for SMSService.");
        }
        return smsMap.get(MultientyConstants.SMS_SERVICE_BEAN_PREFIX + name);
    }

    public static SMSService getService() {
        YamlSMSProperties properties = SpringUtil.getBean(YamlSMSProperties.class);
        if(properties == null) {
            return null;
        }
        return getService(properties.getCurrentProvider());
    }

    /**
     * 获取可选列表
     *
     * @return
     */
    public static Set<String> getSelectable() {
        Map<String, SMSService> smsMap = SpringUtil.getApplicationContext().getBeansOfType(SMSService.class);
        return smsMap.keySet();
    }


}
