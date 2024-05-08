package com.chia.multienty.core.strategy.pay.weixin.sdk;

import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.util.SpringUtil;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.refund.RefundService;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WxPayMerchantProvider {
    private static final Map<Long, RSAAutoCertificateConfig> configMap = new ConcurrentHashMap<>();
    private static final Map<Long, JsapiServiceExtension> jsapiServiceExtensionMap = new ConcurrentHashMap<>();
    private static final Map<Long, RefundService> refundServiceMap = new ConcurrentHashMap<>();


    public static RSAAutoCertificateConfig getApiConfig(WechatAppDTO app) {
        if(configMap.containsKey(app.getProgramId())) {
            return configMap.get(app.getProgramId());
        }
        YamlMultientyProperties properties = SpringUtil.getBean(YamlMultientyProperties.class);
        String separator = File.separator;
        String privateKeyPath = app.getPay().getPrivateKeyPath();

        if(SpringUtil.isLinux()) {
            privateKeyPath = privateKeyPath.replaceAll("\\\\", separator);
        }
        String privateKeyFilePath = Paths.get(
                properties.getFile().getLocal().get("path-prefix"),
                privateKeyPath).toString();
        RSAAutoCertificateConfig config = new RSAAutoCertificateConfig.Builder()
                .merchantId(app.getPay().getMchId())
                .privateKeyFromPath(privateKeyFilePath)
                .merchantSerialNumber(app.getPay().getSerialNumber())
                .apiV3Key(app.getPay().getApiV3Key())
                .build();
        configMap.put(app.getProgramId(), config);
        return config;
    }

    public static JsapiServiceExtension getJsApi(WechatAppDTO app) {
        if(jsapiServiceExtensionMap.containsKey(app.getProgramId())) {
            return jsapiServiceExtensionMap.get(app.getProgramId());
        }
        Config config = getApiConfig(app);
        JsapiServiceExtension extension = new JsapiServiceExtension.Builder().config(config).build();
        jsapiServiceExtensionMap.put(app.getProgramId(), extension);
        return extension;
    }

    public static RefundService getRefundService(WechatAppDTO app) {
        if(refundServiceMap.containsKey(app.getProgramId())) {
            return refundServiceMap.get(app.getProgramId());
        }
        Config config = getApiConfig(app);
        RefundService service = new RefundService.Builder().config(config).build();
        refundServiceMap.put(app.getProgramId(), service);
        return service;
    }
}
