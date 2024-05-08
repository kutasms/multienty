package com.chia.multienty.wechat.thirdparty.sdk.response.template;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.template.MppCodeTemplate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 获取模板列表响应数据
 */
@Data
public class TemplateListGetResponse extends BaseResponse {
    /**
     * 模板信息列表
     */
    @JsonProperty("template_list")
    private List<MppCodeTemplate> templateList;
}
