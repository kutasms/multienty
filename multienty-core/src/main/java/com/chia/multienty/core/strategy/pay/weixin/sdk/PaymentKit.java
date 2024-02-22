package com.chia.multienty.core.strategy.pay.weixin.sdk;


import org.apache.commons.io.Charsets;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


/****************************************************
 *
 *  微信支付的统一下单工具类
 *
 * @author majker
 * @version 1.0
 **************************************************/
public class PaymentKit {

   /**
    * 组装签名的字段
    * @param params 参数
    * @param urlEncoder 是否urlEncoder
    * @return String
    */
   public static String packageSign(Map<String, Object> params, boolean urlEncoder) {
       // 先将参数以其参数名的字典序升序进行排序
       TreeMap<String, Object> sortedParams = new TreeMap<String, Object>(params);
       // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
       StringBuilder sb = new StringBuilder();
       boolean first = true;
       for (Entry<String, Object> param : sortedParams.entrySet()) {
           String value = param.getValue().toString();
           if (Tools.isEmpty(value)) {
               continue;
           }
           if (first) {
               first = false;
           } else {
               sb.append("&");
           }
           sb.append(param.getKey()).append("=");
           if (urlEncoder) {
               try { value = urlEncode(value); } catch (UnsupportedEncodingException e) {}
           }
           sb.append(value);
       }
       return sb.toString();
   }

   /**
    * urlEncode
    * @param src 微信参数
    * @return String
    * @throws UnsupportedEncodingException 编码错误
    */
   public static String urlEncode(String src) throws UnsupportedEncodingException {
       return URLEncoder.encode(src, Charsets.UTF_8.name()).replace("+", "%20");
   }

   /**
    * 生成签名
    * @param params 参数
    * @param partnerKey 支付密钥
    * @return sign
    */
   public static String createSign(Map<String, Object> params, String partnerKey) {
       // 生成签名前先去除sign
       params.remove("sign");
       String stringA = packageSign(params, false);
       String stringSignTemp = stringA + "&key=" + partnerKey;
       return Tools.md5(stringSignTemp).toUpperCase();
   }

   /**
    * 支付异步通知时校验sign
    * @param params 参数
    * @param partnerKey 支付密钥
    * @return {boolean}
    */
   public static boolean verifyNotify(Map<String, Object> params, String partnerKey){
       String sign = params.get("sign").toString();
       String localSign = PaymentKit.createSign(params, partnerKey);
       return sign.equals(localSign);
   }

   /**
    * 微信下单，map to xml
    * @param params 参数
    * @return String
    */
   public static String toXml(Map<String, Object> params) {
       StringBuilder xml = new StringBuilder();
       xml.append("<xml>");
       for (Entry<String, Object> entry : params.entrySet()) {
           String key   = entry.getKey();
           String value = entry.getValue().toString();
           // 略过空值
           if (Tools.isEmpty(value)) continue;
           xml.append("<").append(key).append(">");
           xml.append("<![CDATA["+entry.getValue()+"]]>");
           xml.append("</").append(key).append(">");
       }
       xml.append("</xml>");
       return xml.toString();
   }

   /**
    * 针对支付的xml，没有嵌套节点的简单处理
    * @param xmlStr xml字符串
    * @return map集合
    */
   public static Map<String, Object> xmlToMap(String xmlStr) {
       XmlHelper xmlHelper = XmlHelper.of(xmlStr);
       return xmlHelper.toMap();
   }

}
