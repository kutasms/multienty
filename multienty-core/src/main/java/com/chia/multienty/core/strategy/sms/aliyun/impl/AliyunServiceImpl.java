package com.chia.multienty.core.strategy.sms.aliyun.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.chia.multienty.core.domain.enums.SMSProviderType;
import com.chia.multienty.core.strategy.sms.aliyun.AliyunService;
import com.chia.multienty.core.strategy.sms.aliyun.domain.entity.AliyunSMSOrigResponse;
import com.chia.multienty.core.strategy.sms.domain.GenericContent;
import com.chia.multienty.core.strategy.sms.domain.SMSResult;
import com.chia.multienty.core.strategy.sms.domain.VerificationCodeBO;
import com.chia.multienty.core.strategy.sms.properties.YamlSMSProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AliyunServiceImpl implements AliyunService {

    private final String DEBUG_VERIFICATION_CODE = "9999";
    private final String DEBUG_TEXT = "test";
    @Autowired
    private YamlSMSProperties properties;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getType() {
        return SMSProviderType.ALIYUN.name();
    }

    @Override
    @SneakyThrows
    public SMSResult send(GenericContent content) {
        if(properties.getRunningMode().equals("debug")) {
            log.info("调试模式，发送9999");
            return new SMSResult().setData(DEBUG_TEXT).setOrigResult(null);
        }
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", properties.getAliyun().getConnectTimeout());
        System.setProperty("sun.net.client.defaultReadTimeout", properties.getAliyun().getReadTimeout());
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(properties.getAliyun().getRegionId(),
                properties.getAliyun().getKeyId(), properties.getAliyun().getKeySecret());
        IAcsClient acsClient = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);//注意这四行，与官网所展示的方法不同
        request.setDomain(properties.getAliyun().getDomain());
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", properties.getAliyun().getRegionId());
        request.putQueryParameter("PhoneNumbers",
                String.join(",", content.getTargetPhoneNumbers())
        );
        request.putQueryParameter("SignName", properties.getAliyun().getSignName());
        request.putQueryParameter("TemplateCode", properties.getAliyun().getTemplates().get(content.getTemplateKey()));
        request.putQueryParameter("TemplateParam", JSON.toJSONString(content.getParameters()));
        CommonResponse response = acsClient.getCommonResponse(request);
        log.info("阿里云短信返回:{}", response.getData());
        AliyunSMSOrigResponse origResponse = objectMapper.readValue(response.getData(), AliyunSMSOrigResponse.class);
        return new SMSResult().setSucceeded(origResponse.getMessage().equals("OK")).setOrigResult(response.getData()).setData(content);
    }

    @Override
    public SMSResult sendVerificationCode(VerificationCodeBO verificationCodeBO) throws ClientException {
        if(properties.getRunningMode().equals("debug")) {
            log.info("调试模式，发送9999");
            return new SMSResult().setData(DEBUG_VERIFICATION_CODE).setOrigResult(null);
        }
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", properties.getAliyun().getConnectTimeout());
        System.setProperty("sun.net.client.defaultReadTimeout", properties.getAliyun().getReadTimeout());

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(properties.getAliyun().getRegionId(),
                properties.getAliyun().getKeyId(), properties.getAliyun().getKeySecret());
        IAcsClient acsClient = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);//注意这四行，与官网所展示的方法不同
        request.setDomain(properties.getAliyun().getDomain());
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", properties.getAliyun().getRegionId());
        request.putQueryParameter("PhoneNumbers",
                String.join(",", verificationCodeBO.getTargetPhoneNumbers())
        );
        request.putQueryParameter("SignName", properties.getAliyun().getSignName());
        request.putQueryParameter("TemplateCode", properties.getAliyun().getTemplates().get("verification-code"));
        request.putQueryParameter("TemplateParam", JSON.toJSONString(verificationCodeBO));
        CommonResponse response = acsClient.getCommonResponse(request);
        log.info("阿里云短信返回:{}", response.getData());

        return new SMSResult().setSucceeded(true).setOrigResult(response.getData()).setData(verificationCodeBO.getCode());
    }
}
