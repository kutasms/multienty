package com.chia.multienty.wechat.pay.sdk.applyment;

import com.chia.multienty.wechat.pay.sdk.applyment.model.ApplymentIncomingRequest;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.http.*;
import lombok.RequiredArgsConstructor;

import static com.wechat.pay.java.core.util.GsonUtil.toJson;

@RequiredArgsConstructor
public class JsapiService {
    private final HttpClient httpClient;
    private final HostName hostName;

    public static class Builder {

        private HttpClient httpClient;
        private HostName hostName;
        /**
         * 设置请求配置，以该配置构造默认的httpClient，若未调用httpClient()方法，则必须调用该方法
         *
         * @param config 请求配置
         * @return Builder
         */
        public JsapiService.Builder config(Config config) {
            this.httpClient = new DefaultHttpClientBuilder().config(config).build();
            return this;
        }
        /**
         * 设置微信支付域名，可选，默认为api.mch.weixin.qq.com
         *
         * @param hostName 微信支付域名
         * @return Builder
         */
        public JsapiService.Builder hostName(HostName hostName) {
            this.hostName = hostName;
            return this;
        }
        /**
         * 设置自定义httpClient，若未调用config()，则必须调用该方法
         *
         * @param httpClient httpClient
         * @return Builder
         */
        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }
        /**
         * 构造服务
         *
         * @return JsapiService
         */
        public JsapiService build() {
            return new JsapiService(httpClient, hostName);
        }
    }

    /**
     * 特约商户进件
     * @param request 请求参数
     */
    public void incoming(ApplymentIncomingRequest request) {
        String requestPath =
                "https://api.mch.weixin.qq.com/v3/ecommerce/subsidies/create";
        if (this.hostName != null) {
            requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
        headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
        HttpRequest httpRequest =
                new HttpRequest.Builder()
                        .httpMethod(HttpMethod.POST)
                        .url(requestPath)
                        .headers(headers)
                        .body(createRequestBody(request))
                        .build();
        httpClient.execute(httpRequest, null);
    }

    private RequestBody createRequestBody(Object request) {
        return new JsonRequestBody.Builder().body(toJson(request)).build();
    }
}
