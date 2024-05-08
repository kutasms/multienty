package com.chia.multienty.core.util;

import com.chia.multienty.core.domain.basic.HttpResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpUtil {

    private final static String CHARSET_DEFAULT = "UTF-8";

    /**
     * 读取请求数据流
     *
     * @param request
     * @return
     */
    public static String getRequestBody(HttpServletRequest request) {

        StringBuffer sb = new StringBuffer();

        try (ServletInputStream inputStream = request.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error("读取数据流异常:{}", e);
        }
        return sb.toString();
    }

    // 获取客户端ip
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || ip.contains(":")) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                ip = null;
            }
        }
        if (ip != null && ip.length() != 0) {
            String[] ipArr = ip.split(",");
            for (String ipCur:ipArr) {
                if (!ipCur.trim().equals("127.0.0.1")) {
                    ip = ipCur.trim();
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * post请求  编码格式默认UTF-8
     *
     * @param url     请求url
     * @return
     */
    public static HttpResult doPostJson(String url, Object obj) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse resp = null;

        HttpResult result = new HttpResult();
        try {
            HttpPost httpPost = new HttpPost(url);
            ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);
            String json = objectMapper.writeValueAsString(obj);
            log.info("请求微信参数:{}", json);
            HttpEntity entity = new StringEntity( json,
                    ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            resp = httpClient.execute(httpPost);
            String body = EntityUtils.toString(resp.getEntity(), CHARSET_DEFAULT);
            int statusCode = resp.getStatusLine().getStatusCode();
            result.setStatus(statusCode);
            result.setBody(body);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != resp) {
                try {
                    resp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * post请求  编码格式默认UTF-8
     *
     * @param url     请求url
     * @return
     */
    public static HttpResult doPost(String url, Object obj) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse resp = null;

        HttpResult result = new HttpResult();
        try {
            Map<String, String> params = objectToMap(obj);
            HttpPost httpPost = new HttpPost(url);
            if (params != null && params.size() > 0) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(list, CHARSET_DEFAULT));
            }

            resp = httpClient.execute(httpPost);
            String body = EntityUtils.toString(resp.getEntity(), CHARSET_DEFAULT);
            int statusCode = resp.getStatusLine().getStatusCode();

            result.setStatus(statusCode);
            result.setBody(body);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != resp) {
                try {
                    resp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * post请求  编码格式默认UTF-8
     *
     * @param url     请求url
     * @return
     */
    public static HttpResult doPostFile(String url,  File file) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse resp = null;

        HttpResult result = new HttpResult();
        try {

            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            // 把文件加到HTTP的post请求中
            builder.addBinaryBody(
                    "file",
                    new FileInputStream(file),
                    ContentType.MULTIPART_FORM_DATA,
                    file.getName()
            );
            //HttpEntity
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            resp = httpClient.execute(httpPost);
            result.setStatus(resp.getStatusLine().getStatusCode());
            result.setBody(EntityUtils.toString(resp.getEntity(), CHARSET_DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != resp) {
                try {
                    resp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Map<String, String> map = new HashMap<String,String>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String fieldValue = "";
            if (field.getType()== String.class || field.getType() == Integer.class || field.getType() == int.class){
                fieldValue = field.get(obj)==null?"": field.get(obj).toString();
            }else {
                fieldValue = new Gson().toJson(field.get(obj));
            }
            map.put(fieldName, fieldValue);
        }
        return map;
    }
}

