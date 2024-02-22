package com.chia.multienty.core.strategy.pay.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.domain.enums.TerminalType;
import com.chia.multienty.core.dubbo.service.DubboMultiTenantService;
import com.chia.multienty.core.parameter.wechat.WechatAppDetailGetParameter;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.strategy.pay.domain.*;
import com.chia.multienty.core.strategy.pay.domain.request.MTPayOrderCloseRequest;
import com.chia.multienty.core.strategy.pay.domain.request.MTPrepayRequest;
import com.chia.multienty.core.strategy.pay.domain.request.MTRefundRequest;
import com.chia.multienty.core.strategy.pay.domain.response.MTPrepayResponse;
import com.chia.multienty.core.strategy.pay.weixin.sdk.PaymentApi;
import com.chia.multienty.core.strategy.pay.weixin.sdk.PaymentKit;
import com.chia.multienty.core.strategy.pay.weixin.sdk.WXPayUtil;
import com.chia.multienty.core.strategy.pay.weixin.service.WxPayService;
import com.chia.multienty.core.util.KutaBeanUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付
 * */
@Service
@Slf4j(topic = "WxPayServiceImpl")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.kuta.multi-tenant.wechat.pay", name = "active", havingValue = "wechat-v2")
public class WxPayServiceImpl implements WxPayService {


    private final YamlMultiTenantProperties properties;

    private final ObjectMapper objectMapper;

    private final DubboMultiTenantService dubboMultiTenantService;
    /**
     * 微信退款
     * @see <a href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_4">微信支付v2退款接口</a>
     * */
    @Override
    @SneakyThrows
    public MTPayRefund refund(MTRefundRequest req) {

        WechatAppDTO app = dubboMultiTenantService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(req.getProgramId())
                .setContainsTemplates(false));

        //退款资金来源-可用余额退款
        String refundAccount = "REFUND_SOURCE_RECHARGE_FUNDS";
        Map<String, Object> params = new HashMap<>();
        if (req.getTerminalType().equals(TerminalType.MP_WECHAT) || req
                .getTerminalType().equals(TerminalType.H5)) {
            //小程序微信退款
            params.put("appid", app.getMiniAppId());
        } else if (req.getTerminalType().equals(TerminalType.NATIVE_APP)) {
            //APP微信退款
            params.put("appid", app.getNativeAppId());
        }
        params.put("mch_id", app.getPay().getMchId());
        params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
        //商户订单号和微信订单号二选一
//        params.put("out_trade_no", wxPayLog.getOutTradeNo());
        params.put("transaction_id", req.getTransactionId());
        params.put("out_refund_no", req.getOutRefundNo());
        params.put("total_fee", req.getRefund().multiply(BigDecimal.valueOf(100)).intValue() + "");
        params.put("refund_fee", req.getTotal().multiply(BigDecimal.valueOf(100)).intValue() + "");
        params.put("refund_account", refundAccount);
        // 退款原因，若商户传入，会在下发给用户的退款消息中体现退款原因
        params.put("refund_desc", req.getReason());

        //签名算法
        String sign = WXPayUtil.generateSignature(params, app.getPay().getApiV2Key());
        params.put("sign", sign);
        String xml = PaymentKit.toXml(params);
        log.info(xml);
        String xmlStr = WXPayUtil.doRefund("https://api.mch.weixin.qq.com/secapi/pay/refund", xml,
                app.getPay().getCertPath(), app.getPay().getMchId());
        log.info(xmlStr);
        //加入微信支付日志
        Map<String, Object> map = PaymentKit.xmlToMap(xmlStr);
        return KutaBeanUtil.mapToBean(map, MTPayRefund.class);
    }

    @Override
    public MTPrepayResponse prepay(MTPrepayRequest req) throws Exception {
        WechatAppDTO app = dubboMultiTenantService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(req.getProgramId())
                .setContainsTemplates(false));
        Map<String, Object> requestParams = new HashMap<>();
        //生成一个新的订单支付编号
        String outTradeNo = req.getTradeNo();
        if (PaySource.MINI_PROGRAM.equals(req.getSource())) {
            //微信分配的小程序ID
            requestParams.put(WeixinPayConstants.APP_ID, app.getMiniAppId());
            //用户标识
            requestParams.put(WeixinPayConstants.OPEN_ID, req.getOpenid());
            //交易类型
            requestParams.put(WeixinPayConstants.TRADE_TYPE, "JSAPI");
            //附加参数，微信透传，设置支付端为小程序
            requestParams.put(WeixinPayConstants.ATTACH, String.format("{\"source\":%s, \"purpose\":%s}", TerminalType.MP_WECHAT.getValue() , req.getPurpose().getValue()));
        } else if (PaySource.H5.equals(req.getSource())) {
            //微信分配的小程序ID
            requestParams.put(WeixinPayConstants.APP_ID, app.getMiniAppId());
            //用户标识
            requestParams.put(WeixinPayConstants.OPEN_ID, req.getOpenid());
            //交易类型
            requestParams.put(WeixinPayConstants.TRADE_TYPE, "MWEB");
            //附加参数，微信透传，设置支付端为H5
            requestParams.put(WeixinPayConstants.ATTACH,String.format("{\"source\":%s, \"purpose\":%s}", TerminalType.H5.getValue() , req.getPurpose().getValue()));
        } else if(PaySource.NATIVE_APP.equals(req.getSource())) {
            //微信分配的APPID
            requestParams.put(WeixinPayConstants.OPEN_ID, app.getNativeAppId());
            //交易类型
            requestParams.put(WeixinPayConstants.TRADE_TYPE, "APP");
            //附加参数，微信透传，设置支付端为APP
            requestParams.put(WeixinPayConstants.ATTACH, String.format("{\"source\":%s, \"purpose\":%s}", TerminalType.NATIVE_APP.getValue() , req.getPurpose().getValue()));
        }
        //微信支付分配的商户号
        requestParams.put(WeixinPayConstants.MCH_ID, app.getPay().getMchId());
        //随机字符串
        requestParams.put(WeixinPayConstants.NONCE_STR, System.currentTimeMillis() / 1000 + "");
        //签名类型
        requestParams.put(WeixinPayConstants.SIGN_TYPE, "MD5");
        //充值订单 商品描述
        requestParams.put(WeixinPayConstants.BODY, String.format("订单%s微信小程序", req.getTradeNo()));

        //商户订单编号
        requestParams.put(WeixinPayConstants.OUT_TRADE_NO, outTradeNo);
        //订单总金额，单位为分
        requestParams.put(WeixinPayConstants.TOTAL_FEE, req.getMoney().multiply(BigDecimal.valueOf(100)).intValue() + "");
        //终端IP
        requestParams.put(WeixinPayConstants.SPBILL_CREATE_IP, req.getIp());
        //通知地址
        requestParams.put(WeixinPayConstants.NOTIFY_URL,  req.getNotifyUrl());
        //签名
        String sign = WXPayUtil.generateSignature(requestParams, app.getPay().getApiV2Key());
        requestParams.put(WeixinPayConstants.SIGN, sign);
        /*
            调用支付定义下单API,返回预付单信息 prepay_id
         */
        log.info(JSON.toJSONString(requestParams));
        String xmlResult = PaymentApi.pushOrder(requestParams);
        log.info(xmlResult);
        Map<String, Object> result = PaymentKit.xmlToMap(xmlResult);
        //预付单信息
        String prepay_id = result.get(WeixinPayConstants.PREPAY_ID).toString();

        /*
            小程序调起支付数据签名
         */
        Map<String, Object> packageParams = new HashMap<String, Object>();
        packageParams.put("appId", requestParams.get(WeixinPayConstants.APP_ID).toString());
        packageParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
        packageParams.put("nonceStr", System.currentTimeMillis() + "");
        packageParams.put("package", "prepay_id=" + prepay_id);
        packageParams.put("signType", "MD5");
        String packageSign = WXPayUtil.generateSignature(packageParams, app.getPay().getApiV2Key());
        packageParams.put("sign", packageSign);
        packageParams.put("paymentCodeUrl", result.get("code_url"));
        if (PaySource.H5.getValue().equals(req.getSource())) {
            packageParams.put("h5PaymentUrl", result.get("mweb_url") + "&redirect_url=" + URLEncoder.encode(properties.getWechat().getPay().getV2H5RedirectUrl(), "GBK"));
        }
        if (PaySource.NATIVE_APP.getValue().equals(req.getSource())) {
            packageParams.put("partnerId", app.getPay().getMchId());
        }
        MTPrepayResponse response = objectMapper.readValue(objectMapper.writeValueAsString(packageParams), MTPrepayResponse.class);
        return response;
    }

    @Override
    public MTPayTransaction queryOrder(Long programId, String outTradeNo) throws Exception {
        WechatAppDTO app = dubboMultiTenantService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(programId)
                .setContainsTemplates(false));
        Map<String, Object> resultMap = PaymentApi.queryByOutTradeNo(
                app.getMiniAppId(),
                app.getPay().getMchId(),
                app.getPay().getApiV2Key(),
                outTradeNo);
        MTPayTransaction order = objectMapper.readValue(objectMapper.writeValueAsString(resultMap), MTPayTransaction.class);
        return order;
    }

    @Override
    @SneakyThrows
    public MTPayOrderCloseResult closeOrder(MTPayOrderCloseRequest req) {
        WechatAppDTO app = dubboMultiTenantService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(req.getProgramId())
                .setContainsTemplates(false));
        Map<String, Object> resultMap = PaymentApi.closeOrder(app.getMiniAppId(),
                app.getPay().getMchId(),
                app.getPay().getApiV2Key(),
                req.getOutTradeNo());
        MTPayOrderCloseResult result = objectMapper.readValue(
                objectMapper.writeValueAsString(resultMap), MTPayOrderCloseResult.class);

        return result;
    }

    @Override
    @SneakyThrows
    public MTPayRefund queryRefund(Long programId, String outRefundNo) {
        WechatAppDTO app = dubboMultiTenantService.getWechatApp(new WechatAppDetailGetParameter()
                .setContainsPay(true)
                .setProgramId(programId)
                .setContainsTemplates(false));
        Map<String, Object> resultMap = PaymentApi.refundQueryByOutRefundNo(app.getMiniAppId(),
                app.getPay().getMchId(),
                app.getPay().getApiV2Key(),
                outRefundNo);
        MTPayRefund refund = objectMapper.readValue(
                objectMapper.writeValueAsString(resultMap), MTPayRefund.class);
        refund.setOrigMap(resultMap);
        return refund;
    }

}
