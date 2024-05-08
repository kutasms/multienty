
package com.chia.multienty.core.strategy.pay;


import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.strategy.pay.domain.PayType;
import com.chia.multienty.core.util.SpringUtil;

import java.util.Map;

public class PayFactory {

    public static PayService getPayService(PayType paymentType) throws KutaRuntimeException {
        if (paymentType != null) {
            Map<String, PayService> payServiceMap = SpringUtil.getApplicationContext().getBeansOfType(PayService.class);
            return payServiceMap.get(MultientyConstants.PAY_SERVICE_BEAN_PREFIX + paymentType.name());
        }
        throw new KutaRuntimeException(HttpResultEnum.SYSTEM_ERROR);
    }

}
