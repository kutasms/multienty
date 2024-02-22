package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.CommitResponse;
import lombok.Data;

/**
 * 上传代码并生成体验版
 */
@Data
@WxApi(url = MerchantApis.Code.COMMIT)
public class CommitRequest extends AuthorizerBaseRequest<CommitResponse> {
    /**
     * 代码库中的代码模板 ID，可通过getTemplateList接口获取代
     * 码模板template_id。注意，如果该模板id为标准模板库的模板
     * id，则ext_json可支持的参数为：
     * {"extAppid":" ", "ext": {}, "window": {}}
     */
    private Long templateId;
    /**
     * 为了方便第三方平台的开发者引入 extAppid 的开发调试工作，
     * 引入ext.json配置文件概念，该参数则是用于控制ext.json配
     * 置文件的内容。关于该参数的补充说明请查看下方的"ext_json
     * 补充说明"
     */
    private String extJson;
    /**
     * 代码版本号，开发者可自定义（长度不要超过 64 个字符）
     */
    private String userVersion;
    /**
     * 代码描述，开发者可自定义
     */
    private String userDesc;
}
