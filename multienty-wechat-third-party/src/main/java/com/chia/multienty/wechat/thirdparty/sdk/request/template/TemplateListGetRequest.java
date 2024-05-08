package com.chia.multienty.wechat.thirdparty.sdk.request.template;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplateListGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取模板列表
 */
@Data
@WxApi(url = ThirdPlatformApis.Template.GET_TEMPLATE_LIST, method = WxApi.Method.GET)
public class TemplateListGetRequest extends ComponentBaseRequest<TemplateListGetResponse> {
    /**
     * 默认值是0，对应普通模板；可选1，对应标准模板库，关于标准模板库和普通模板库的区别可以查看小程序模板库介绍
     */
    @JsonProperty("template_type")
    private Integer templateType;
}
