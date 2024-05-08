package com.chia.multienty.wechat.thirdparty.define.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户生成内容场景（UGC）信息安全声明
 */
@Data
public class CodeAuditUGCDeclare {
    /**
     * UGC场景 0,不涉及用户生成内容, 1.用户资料,2.图片,3.视频,4.文本,5音频, 可多选,当scene填0时无需填写下列字段
     */
    @JsonProperty("scene")
    private List<Integer> scene;
    /**
     * 内容安全机制 1.使用平台建议的内容安全API,2.使用其他的内容审核产品,3.通过人工审核把关,4.未做内容审核把关
     */
    @JsonProperty("method")
    private List<Integer> method;
    /**
     * 当scene选其他时的说明,不超时256字
     */
    @JsonProperty("other_scene_desc")
    private String otherSceneDesc;
    /**
     * 是否有审核团队, 0.无,1.有,默认0
     */
    @JsonProperty("has_audit_team")
    private Integer hasAuditTeam;
    /**
     * 说明当前对UGC内容的审核机制,不超过256字
     */
    @JsonProperty("audit_desc")
    private String auditDesc;
}
