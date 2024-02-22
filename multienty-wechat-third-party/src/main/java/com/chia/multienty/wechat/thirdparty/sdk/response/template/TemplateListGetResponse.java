package com.chia.multienty.wechat.thirdparty.sdk.response.template;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.template.MppCodeTemplate;
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
    private List<MppCodeTemplate> templateList;
}
