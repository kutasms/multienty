package com.chia.multienty.wechat.thirdparty.sdk.tools;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WxTPAuthTool {

    private static final String PATTERN_PC_URL = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%s&pre_auth_code=%s&redirect_uri=%s&auth_type=%s";

//    /**
//     * 获取小程序授权链接
//     * @return
//     */
//    @SneakyThrows
//    public static String getMiniProgramAuthUrl(Long tenantId) {
//        WxTPApiExecutor executor = SpringUtil.getBean(WxTPApiExecutor.class);
//        YamlMultientyProperties properties = SpringUtil.getBean(YamlMultientyProperties.class);
//        PreAuthCodeGetRequest request = new PreAuthCodeGetRequest();
//        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
//        PreAuthCodeGetResponse response = executor.execute(request);
//
//        WechatAppService wechatAppService = SpringUtil.getBean(WechatAppService.class);
//        WechatApp app = wechatAppService.getWaiting(tenantId);
//        if(app == null) {
//            app = new WechatApp();
//            app.setTenantId(tenantId);
//            app.setPreAuthCode(response.getPreAuthCode());
//            app.setStatus(StatusEnum.CREATED.getCode());
//            wechatAppService.saveTE(app);
//        } else {
//            app.setPreAuthCode(response.getPreAuthCode());
//            app.setCreateTime(LocalDateTime.now());
//            wechatAppService.updateByIdTE(app);
//        }
//
//        String encodedUrl = URLEncoder.encode(properties.getWechat().getThirdParty().getAuthRedirectUri(), "UTF-8");
//        return String.format(PATTERN_PC_URL,
//                properties.getWechat().getThirdParty().getAppId(),
//                response.getPreAuthCode(),
//                encodedUrl,
//                2);
//    }
}
