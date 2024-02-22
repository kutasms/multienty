
package com.chia.multienty.core.strategy.pay;


import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.strategy.pay.domain.PayType;
import com.chia.multienty.core.strategy.pay.weixin.service.WxV3PayService;

public class PayFactory {

    public static PayService getPayService(PayType paymentType) throws KutaRuntimeException {
        if (paymentType != null) {
            if (paymentType.equals(PayType.WECHAT)) {
                return SpringUtil.getBean(WxV3PayService.class);
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.SYSTEM_ERROR);
    }

}
