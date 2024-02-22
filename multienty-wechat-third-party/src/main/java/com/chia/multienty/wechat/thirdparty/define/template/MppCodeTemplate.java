package com.chia.multienty.wechat.thirdparty.define.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MppCodeTemplate {
    /**
     * 开发者上传草稿时间戳
     */
    private Long createTime;
    /**
     * 版本号，开发者自定义字段
     */
    private String userVersion;
    /**
     * 版本描述 开发者自定义字段
     */
    private String userDesc;
    /**
     * 模板 id
     */
    private Long templateId;
    /**
     * 草稿 id
     */
    private Long draftId;
    /**
     * 开发小程序的appid
     */
    @JsonProperty(value = "source_miniprogram_appid")
    private String sourceMiniProgramAppId;

    /**
     * 开发小程序的名称
     */
    @JsonProperty(value = "source_miniprogram")
    private String sourceMiniProgram;
    /**
     * 0对应普通模板，1对应标准模板
     */
    private Integer templateType;

    /**
     * 标准模板的类目信息；如果是普通模板则值为空的数组
     */
    private List<MppTemplateCategory> categoryList;
    /**
     * 标准模板的场景标签；普通模板不返回该值
     */
    private Long auditScene;
    /**
     * 标准模板的审核状态；普通模板不返回该值
     */
    private Integer auditStatus;
    /**
     * 标准模板的审核驳回的原因，普通模板不返回该值
     */
    private String reason;
}
