package com.chia.multienty.core.strategy.pay;

import com.chia.multienty.core.strategy.pay.domain.MTPayRefund;
import com.chia.multienty.core.strategy.pay.domain.MTPayTransaction;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.strategy.pay.domain.request.MTPayOrderCloseRequest;
import com.chia.multienty.core.strategy.pay.domain.request.MTPrepayRequest;
import com.chia.multienty.core.strategy.pay.domain.request.MTRefundRequest;
import com.chia.multienty.core.strategy.pay.domain.response.MTPrepayResponse;

public interface PayService {

    /**
     * 售后微信退款
     * @see <a href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_4">微信支付V2售后退款</a>
     * @param req 退款参数
     * @throws KutaRuntimeException
     * @throws Exception
     */
    MTPayRefund refund(MTRefundRequest req);


    /**
     * 下单
     * @param req 下单参数
     * @return
     * @throws Exception
     */
    MTPrepayResponse prepay(MTPrepayRequest req) throws Exception;

    /**
     * 主动查询订单
     * @param programId 租户微信应用编号
     * @param outTradeNo 外部交易号
     * @return
     */
    MTPayTransaction queryOrder(Long programId, String outTradeNo) throws Exception;

    /**
     * 关闭订单
     * @param req 订单关闭参数
     * @return
     * @param <Rsp> 返回类型
     */
    <Rsp> Rsp closeOrder(MTPayOrderCloseRequest req);
    /**
     * 查询退款
     * @param programId 租户微信应用编号
     * @param outRefundNo 外部交易号
     * @return
     */
    MTPayRefund queryRefund(Long programId, String outRefundNo);

}
