package com.chia.multienty.wechat.thirdparty.define.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MppCodeTemplate {
    /**
     * 开发者上传草稿时间戳
     */
    @JsonProperty("create_time")
    private Long createTime;
    /**
     * 版本号，开发者自定义字段
     */
    @JsonProperty("user_version")
    private String userVersion;
    /**
     * 版本描述 开发者自定义字段
     */
    @JsonProperty("user_desc")
    private String userDesc;
    /**
     * 开发者
     */
    @JsonProperty("developer")
    private String developer;
    /**
     * 模板 id
     */
    @JsonProperty("template_id")
    private Long templateId;
    /**
     * 草稿 id
     */
    @JsonProperty("draft_id")
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
    @JsonProperty("template_type")
    private Integer templateType;

    /**
     * 标准模板的类目信息；如果是普通模板则值为空的数组
     */
    @JsonProperty("category_list")
    private List<MppTemplateCategory> categoryList;
    /**
     * 标准模板的场景标签；普通模板不返回该值
     */
    @JsonProperty("audit_scene")
    private Long auditScene;
    /**
     * 标准模板的审核状态；普通模板不返回该值
     */
    @JsonProperty("audit_status")
    private Integer auditStatus;
    /**
     * 标准模板的审核驳回的原因，普通模板不返回该值
     */
    @JsonProperty("reason")
    private String reason;
}
