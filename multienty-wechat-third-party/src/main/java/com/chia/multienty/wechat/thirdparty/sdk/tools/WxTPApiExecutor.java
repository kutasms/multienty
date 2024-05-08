package com.chia.multienty.wechat.thirdparty.sdk.tools;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.util.JavaObjectUtil;
import com.chia.multienty.core.util.KutaBeanUtil;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.annotation.WxFormData;
import com.chia.multienty.wechat.thirdparty.annotation.WxUrlEncoding;
import com.chia.multienty.wechat.thirdparty.exception.WxThirdPartyException;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.IWxRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.StreamingResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.lang.Strings;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;

@Slf4j
public class WxTPApiExecutor {
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    public <Rsp> Rsp execute(IWxRequest<Rsp> request) {
        Map<String, Object> map = objectMapper.convertValue(request, new TypeReference<Map<String, Object>>() {
        });
        WxApi wxApi = request.getClass().getAnnotation(WxApi.class);
        String url = wxApi.url();
        if(wxApi.url().contains("%s")) {
            if(request instanceof AuthorizerBaseRequest) {
                AuthorizerBaseRequest authorizerReq = (AuthorizerBaseRequest) request;
                url = String.format(wxApi.url(), authorizerReq.getAccessToken());
            } else {
                WxTPTokenProvider provider = SpringUtil.getBean(WxTPTokenProvider.class);
                url = String.format(wxApi.url(), provider.getComponentAccessToken());
            }
        }

        Field[] fields = KutaBeanUtil.getAllDeclaredFields(request.getClass());
        tryUrlEncoding(request, fields);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        if(wxApi.postForm()) {
            // post form
            String urlParams = JavaObjectUtil.object2UrlParams(request);
            url = url + "&" + urlParams;
        }

        if(wxApi.method().equals(WxApi.Method.GET)) {
            HttpGet httpGet = null;
            String urlParams = JavaObjectUtil.object2UrlParams(request);
            if(Strings.hasText(urlParams)) {
                httpGet = new HttpGet(url + (url.endsWith("&") ? "" : "&") + urlParams);
            } else {
                httpGet = new HttpGet(url);
            }
            log.info("请求第三方平台地址:{}", httpGet.getURI().toString());
            response = httpClient.execute(httpGet);
        } else {
            HttpPost httpPost = new HttpPost(url);
            // 尝试转换formData
            HttpEntity httpEntity = tryParseFormData(request, map, fields);
            if(null != httpEntity) {
                httpPost.setEntity(httpEntity);
            } else {
                String json = objectMapper.writeValueAsString(map);
                log.info("请求微信第三方平台参数:{}", json);
                HttpEntity entity = new StringEntity( json,
                        ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
        }
       Type type = request.getClass().getGenericSuperclass();

        if(type == null || type.getTypeName().equals("java.lang.Object")) {
            type = request.getClass().getGenericInterfaces()[0];
        }

        ParameterizedType parameterizedType = (ParameterizedType)type;
        return this.parseRsp(Class.forName(parameterizedType.getActualTypeArguments()[0].getTypeName()), response);
    }

    @SneakyThrows
    private <Rsp> Rsp parseRsp(Class clazz,CloseableHttpResponse response) {
        try{
            TypeReference<Rsp> reference = new TypeReference<Rsp>() {};
            HttpEntity entity = response.getEntity();
            String contentType = response.getHeaders("Content-Type")[0].getValue();
            if(!contentType.contains("application/json") && !contentType.contains("text/plain")) {
                Rsp rsp = (Rsp)clazz.newInstance();
                if(rsp instanceof StreamingResponse) {
                    StreamingResponse streamingRsp = (StreamingResponse) rsp;
                    byte[] bytes = EntityUtils.toByteArray(entity);
                    streamingRsp.setStream(bytes);
                    return rsp;
                } else {
                    throw new WxThirdPartyException(HttpResultEnum.RESPONSE_TYPE_ERROR.getCode(),
                            HttpResultEnum.RESPONSE_TYPE_ERROR.getMessage());
                }
            } else {
                String rspString = EntityUtils.toString(entity);
                Rsp rsp = (Rsp) objectMapper.readValue(rspString, clazz);
                if(rsp instanceof BaseResponse) {
                    BaseResponse baseResponse = (BaseResponse) rsp;
                    if(!baseResponse.isSucceed()) {
                        throw new WxThirdPartyException(baseResponse.getErrCode(), baseResponse.getErrMsg());
                    }
                }
                return rsp;
            }
        }
        finally {
            response.close();
        }
    }

    private void tryUrlEncoding(Object request, Field[] fields) throws IllegalAccessException, UnsupportedEncodingException {
        for (Field field : fields) {
            if(field.getAnnotation(WxUrlEncoding.class) != null) {
                field.setAccessible(true);
                Object val = field.get(request);
                if(val != null) {
                    field.set(request, URLEncoder.encode(val.toString(), "UTF-8"));
                }
                field.setAccessible(false);
            }
        }
    }

    @SneakyThrows
    private HttpEntity tryParseFormData(Object request, Map<String, Object> map, Field[] fields) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (Field field : fields) {
            WxFormData formData = field.getAnnotation(WxFormData.class);
            if(formData != null) {
                field.setAccessible(true);
                Object fieldVal = field.get(request);
                field.setAccessible(false);
                if(null == fieldVal) {
                    throw new KutaRuntimeException(HttpResultEnum.ARGUMENT_ERROR);
                }
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                if(fieldVal instanceof String) {
                    builder.addBinaryBody(formData.alias(), new File(fieldVal.toString()));
                } else if(fieldVal instanceof MultipartFile) {
                    MultipartFile mpFile = (MultipartFile) fieldVal;
                    builder.addBinaryBody(formData.alias(), mpFile.getBytes());
                }
                return builder.build();
            }
        }

        return null;
    }
}
