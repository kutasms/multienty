package com.chia.multienty.core.strategy.pay.weixin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.domain.enums.TerminalType;
import com.chia.multienty.core.dubbo.service.DubboMultiTenantService;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.wechat.WechatAppDetailGetParameter;
import com.chia.multienty.core.strategy.pay.domain.MTPayOrderCloseResult;
import com.chia.multienty.core.strategy.pay.domain.MTPayRefund;
import com.chia.multienty.core.strategy.pay.domain.MTPayTransaction;
import com.chia.multienty.core.strategy.pay.domain.request.MTPayOrderCloseRequest;
import com.chia.multienty.core.strategy.pay.domain.request.MTPrepayRequest;
import com.chia.multienty.core.strategy.pay.domain.request.MTRefundRequest;
import com.chia.multienty.core.strategy.pay.domain.response.MTPrepayResponse;
import com.chia.multienty.core.strategy.pay.weixin.service.WxV3PayService;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.exception.HttpException;
import com.wechat.pay.java.core.exception.MalformedMessageException;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.AmountReq;
import com.wechat.pay.java.service.refund.model.CreateRequest;
import com.wechat.pay.java.service.refund.model.QueryByOutRefundNoRequest;
import com.wechat.pay.java.service.refund.model.Refund;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.kuta.multi-tenant.wechat.pay", name = "active", havingValue = "wechat-v3")
public class WxV3PayServiceImpl implements WxV3PayService {

    private Map<Long, JsapiServiceExtension> extensionMap = new HashMap<>();

    private Map<Long, RefundService> refundServiceMap = new HashMap<>();

    private final DubboMultiTenantService dubboMultiTenantService;

    @Override
    public MTPayRefund refund(MTRefundRequest req)  {
        WechatAppDTO app = dubboMultiTenantService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(req.getProgramId())
                .setContainsTemplates(false));
        CreateRequest request = new CreateRequest();
        request.setTransactionId(req.getTransactionId());
        request.setOutRefundNo(req.getOutRefundNo());
        request.setReason(req.getReason());
        request.setNotifyUrl(req.getNotifyUrl());
        AmountReq amountReq = new AmountReq();
        amountReq.setCurrency("CNY");
        amountReq.setRefund(req.getRefund().multiply(BigDecimal.valueOf(100)).longValue());
        amountReq.setTotal(req.getTotal().multiply(BigDecimal.valueOf(100)).longValue());
        request.setAmount(amountReq);
        log.info("构建退款参数:{}", JSONObject.toJSONString(request));
        Refund refund = getRefundService(app).create(request);
        MTPayRefund mpr = new MTPayRefund();
        BeanUtils.copyProperties(refund, mpr);
        return mpr;
    }

    @Override
    public MTPrepayResponse prepay(MTPrepayRequest req) throws Exception {
        WechatAppDTO app = dubboMultiTenantService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(req.getProgramId())
                .setContainsTemplates(false));
        try{
            String attachTemplate = "{\"source\":%s, \"purpose\":%s, \"tenant_id\": %s}";
            String attach = null;
            switch (req.getSource()) {
                case MINI_PROGRAM:
                    attach = String.format(attachTemplate, TerminalType.MP_WECHAT.getValue() , req.getPurpose().getValue(), req
                            .getTenantId());
                    break;
                case H5:
                    attach = String.format(attachTemplate, TerminalType.H5.getValue() ,
                            req.getPurpose().getValue(), req.getTenantId());
                    break;
                case NATIVE_APP:
                    attach = String.format(attachTemplate, TerminalType.NATIVE_APP.getValue() ,
                            req.getPurpose().getValue(), req.getTenantId());
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
            request.setNotifyUrl(req.getNotifyUrl());
            request.setOutTradeNo(req.getTradeNo());
            Payer payer = new Payer();
            payer.setOpenid(req.getOpenid());
            request.setPayer(payer);
            PrepayWithRequestPaymentResponse rsp = getJsApi(app).prepayWithRequestPayment(request);
            MTPrepayResponse response = new MTPrepayResponse();
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
        WechatAppDTO app = dubboMultiTenantService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(programId)
                .setContainsTemplates(false));
        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        request.setMchid(app.getPay().getMchId());
        request.setOutTradeNo(outTradeNo);
        Transaction transaction = getJsApi(app).queryOrderByOutTradeNo(request);
        MTPayTransaction mpt = new MTPayTransaction();
        BeanUtils.copyProperties(transaction, mpt);
        mpt.setV3TradeType(transaction.getTradeType());
        return mpt;
    }

    @Override
    public MTPayOrderCloseResult closeOrder(MTPayOrderCloseRequest req) {
        WechatAppDTO app = dubboMultiTenantService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(req.getProgramId())
                .setContainsTemplates(false));
        CloseOrderRequest request = new CloseOrderRequest();
        request.setMchid(app.getPay().getMchId());
        request.setOutTradeNo(req.getOutTradeNo());
        getJsApi(app).closeOrder(request);
        MTPayOrderCloseResult result = new MTPayOrderCloseResult();
        result.setResultCode(StatusEnum.SUCCESS.getCode());
        result.setReturnCode(StatusEnum.SUCCESS.getCode());
        return result;
    }

    @Override
    public MTPayRefund queryRefund(Long programId, String outRefundNo) {
        WechatAppDTO app = dubboMultiTenantService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(programId)
                .setContainsTemplates(false));
        QueryByOutRefundNoRequest request = new QueryByOutRefundNoRequest();
        request.setOutRefundNo(outRefundNo);
        request.setSubMchid(app.getPay().getMchId());
        Refund refund = getRefundService(app).queryByOutRefundNo(request);
        MTPayRefund mpr = new MTPayRefund();
        BeanUtils.copyProperties(refund, mpr);
        return mpr;
    }

    private RSAAutoCertificateConfig getApiConfig(WechatAppDTO app) {
        RSAAutoCertificateConfig config = new RSAAutoCertificateConfig.Builder()
                .merchantId(app.getPay().getMchId())
                .privateKeyFromPath(app.getPay().getPrivateKeyPath())
                .merchantSerialNumber(app.getPay().getSerialNumber())
                .apiV3Key(app.getPay().getApiV3Key())
                .build();
        return config;
    }

    private JsapiServiceExtension getJsApi(WechatAppDTO app) {
        if(extensionMap.containsKey(app.getTenantId())) {
            return extensionMap.get(app.getTenantId());
        }

        RSAAutoCertificateConfig config = getApiConfig(app);
        JsapiServiceExtension extension = new JsapiServiceExtension.Builder().config(config).build();
        extensionMap.put(app.getTenantId(), extension);
        return extension;
    }

    private RefundService getRefundService(WechatAppDTO app) {
        if(refundServiceMap.containsKey(app.getTenantId())) {
            return refundServiceMap.get(app.getTenantId());
        }
        RSAAutoCertificateConfig config = getApiConfig(app);
        RefundService service = new RefundService.Builder().config(config).build();
        refundServiceMap.put(app.getTenantId(), service);
        return service;
    }

}
