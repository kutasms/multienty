package com.chia.multienty.core.strategy.pay.weixin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.domain.enums.TerminalType;
import com.chia.multienty.core.dubbo.service.DubboMultientyService;
import com.chia.multienty.core.dubbo.service.DubboWechatService;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.wechat.WechatAppDetailGetParameter;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.strategy.pay.domain.MTPayOrderCloseResult;
import com.chia.multienty.core.strategy.pay.domain.MTPayRefund;
import com.chia.multienty.core.strategy.pay.domain.MTPayTransaction;
import com.chia.multienty.core.strategy.pay.domain.PayType;
import com.chia.multienty.core.strategy.pay.domain.request.MTPayOrderCloseRequest;
import com.chia.multienty.core.strategy.pay.domain.request.MTPrepayRequest;
import com.chia.multienty.core.strategy.pay.domain.request.MTRefundRequest;
import com.chia.multienty.core.strategy.pay.domain.response.MTPrepayResponse;
import com.chia.multienty.core.strategy.pay.weixin.sdk.WxPayMerchantProvider;
import com.chia.multienty.core.strategy.pay.weixin.service.WxV3PayService;
import com.chia.multienty.core.tools.MultientyContext;
import com.wechat.pay.java.core.exception.HttpException;
import com.wechat.pay.java.core.exception.MalformedMessageException;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.AmountReq;
import com.wechat.pay.java.service.refund.model.CreateRequest;
import com.wechat.pay.java.service.refund.model.QueryByOutRefundNoRequest;
import com.wechat.pay.java.service.refund.model.Refund;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class WxV3PayServiceImpl implements WxV3PayService {



    @Autowired(required = false)
    private DubboMultientyService dubboMultientyService;
    @Autowired(required = false)
    private DubboWechatService dubboWechatService;
    @Autowired
    private YamlMultientyProperties properties;

    @Override
    public String getType() {
        return PayType.WECHAT_V3.name();
    }

    @Override
    public MTPayRefund refund(MTRefundRequest req)  {
        WechatAppDTO app = dubboWechatService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(req.getProgramId())
                .setTenantId(MultientyContext.getTenant().getTenantId())
                .setContainsTemplates(false));
        CreateRequest request = new CreateRequest();
        request.setTransactionId(req.getTransactionId());
        request.setOutRefundNo(req.getOutRefundNo());
        request.setReason(req.getReason());
        request.setNotifyUrl(String.format(properties.getWechat().getPay().getV3NotifyUrls().get("order-refund-notify-url"),
                app.getTenantId(),app.getProgramId())
        );
        AmountReq amountReq = new AmountReq();
        amountReq.setCurrency("CNY");
        amountReq.setRefund(req.getRefund().multiply(BigDecimal.valueOf(100)).longValue());
        amountReq.setTotal(req.getTotal().multiply(BigDecimal.valueOf(100)).longValue());
        request.setAmount(amountReq);
        log.info("构建退款参数:{}", JSONObject.toJSONString(request));

        Refund refund = WxPayMerchantProvider.getRefundService(app).create(request);
        MTPayRefund mpr = new MTPayRefund();
        BeanUtils.copyProperties(refund, mpr);
        return mpr;
    }

    @Override
    public MTPrepayResponse prepay(MTPrepayRequest req) throws Exception {
        WechatAppDTO app = dubboWechatService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(req.getProgramId())
                .setTenantId(MultientyContext.getTenant().getTenantId())
                .setContainsTemplates(false));
        try{
            String attachTemplate = "{\"source\":%s, \"purpose\":%s}";
            String attach = null;
            switch (req.getSource()) {
                case MINI_PROGRAM:
                    attach = String.format(attachTemplate, TerminalType.MP_WECHAT.getValue() , req.getPurpose().getValue());
                    break;
                case H5:
                    attach = String.format(attachTemplate, TerminalType.H5.getValue() ,
                            req.getPurpose().getValue());
                    break;
                case NATIVE_APP:
                    attach = String.format(attachTemplate, TerminalType.NATIVE_APP.getValue() ,
                            req.getPurpose().getValue());
                    break;
            }
            Integer finalAmount = req.getMoney().multiply(BigDecimal.valueOf(100)).intValue();
            PrepayRequest request = new PrepayRequest();
            Amount amount = new Amount();
            amount.setTotal(finalAmount);
            request.setAmount(amount);
            request.setAppid(app.getMiniAppId());
            request.setAttach(attach);
            request.setDescription(String.format("订单%s支付", req.getTradeNo()));
            request.setMchid(app.getPay().getMchId());
            request.setNotifyUrl(String.format(properties.getWechat().getPay().getV3NotifyUrls().get("trade-pay-notify-url"),
                    app.getTenantId(),
                    app.getProgramId()));
            request.setOutTradeNo(req.getTradeNo());
            Payer payer = new Payer();
            payer.setOpenid(req.getOpenid());
            request.setPayer(payer);
            PrepayWithRequestPaymentResponse rsp = WxPayMerchantProvider.getJsApi(app).prepayWithRequestPayment(request);
            MTPrepayResponse response = new MTPrepayResponse();
            response.setTimestamp(rsp.getTimeStamp());
            BeanUtils.copyProperties(rsp, response);

            return response;
        } catch (HttpException e) { // 发送HTTP请求失败
            log.error("",e);
            // 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
            throw new KutaRuntimeException(HttpResultEnum.THIRD_PARTY_EXCEPTION);
        } catch (ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
            log.error("",e);
            // 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
            throw new KutaRuntimeException(HttpResultEnum.THIRD_PARTY_EXCEPTION);
        } catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
            log.error("",e);
            // 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
            throw new KutaRuntimeException(HttpResultEnum.THIRD_PARTY_EXCEPTION);
        }
    }

    @Override
    public MTPayTransaction queryOrder(Long programId, String outTradeNo) throws Exception {
        WechatAppDTO app = dubboWechatService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(programId)
                .setTenantId(MultientyContext.getTenant().getTenantId())
                .setContainsTemplates(false));
        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        request.setMchid(app.getPay().getMchId());
        request.setOutTradeNo(outTradeNo);
        Transaction transaction = WxPayMerchantProvider.getJsApi(app).queryOrderByOutTradeNo(request);
        MTPayTransaction mpt = new MTPayTransaction();
        BeanUtils.copyProperties(transaction, mpt);
        mpt.setV3TradeType(transaction.getTradeType());
        return mpt;
    }

    @Override
    public MTPayOrderCloseResult closeOrder(MTPayOrderCloseRequest req) {
        WechatAppDTO app = dubboWechatService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(req.getProgramId())
                .setTenantId(MultientyContext.getTenant().getTenantId())
                .setContainsTemplates(false));
        CloseOrderRequest request = new CloseOrderRequest();
        request.setMchid(app.getPay().getMchId());
        request.setOutTradeNo(req.getOutTradeNo());
        WxPayMerchantProvider.getJsApi(app).closeOrder(request);
        MTPayOrderCloseResult result = new MTPayOrderCloseResult();
        result.setResultCode(StatusEnum.SUCCESS.getCode());
        result.setReturnCode(StatusEnum.SUCCESS.getCode());
        return result;
    }

    @Override
    public MTPayRefund queryRefund(Long programId, String outRefundNo) {
        WechatAppDTO app = dubboWechatService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(programId)
                .setTenantId(MultientyContext.getTenant().getTenantId())
                .setContainsTemplates(false));
        QueryByOutRefundNoRequest request = new QueryByOutRefundNoRequest();
        request.setOutRefundNo(outRefundNo);
        request.setSubMchid(app.getPay().getMchId());
        Refund refund = WxPayMerchantProvider.getRefundService(app).queryByOutRefundNo(request);
        MTPayRefund mpr = new MTPayRefund();
        BeanUtils.copyProperties(refund, mpr);
        return mpr;
    }
}
