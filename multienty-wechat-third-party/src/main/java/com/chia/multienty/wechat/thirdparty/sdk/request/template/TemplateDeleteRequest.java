package com.chia.multienty.wechat.thirdparty.sdk.request.template;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplateDeleteResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 删除代码模版
 */
@Data
@WxApi(url = ThirdPlatformApis.Template.DELETE_TEMPLATE)
public class TemplateDeleteRequest extends ComponentBaseRequest<TemplateDeleteResponse> {
    /**
     * 要删除的模板 ID ，可通过获取模板列表接口获取
     */
    @JsonProperty("template_id")
    private String templateId;
}
