package com.chia.multienty.wechat.thirdparty.sdk.request.template;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.AddToTemplateResponse;
import lombok.Data;

/**
 * 将草稿添加到模板库
 */
@Data
@WxApi(url = ThirdPlatformApis.Template.ADD_TO_TEMPLATE)
public class AddToTemplateRequest extends ComponentBaseRequest<AddToTemplateResponse> {
    /**
     * 草稿 ID
     */
    private Long draftId;
    /**
     * 默认值是0，对应普通模板；可选1，对应标准模板库，关于标准模板库和普通模板库的区别可以查看小程序模板库介绍
     */
    private Integer templateType;
}
