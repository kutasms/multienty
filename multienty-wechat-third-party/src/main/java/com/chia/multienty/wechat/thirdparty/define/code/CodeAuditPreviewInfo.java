package com.chia.multienty.wechat.thirdparty.define.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 预览信息（小程序页面截图和操作录屏）
 */
@Data
public class CodeAuditPreviewInfo {
    /**
     * 录屏mediaid列表，可以通过提审素材上传接口获得
     */
    @JsonProperty("video_id_list")
    private List<String> videoIdList;
    /**
     * 截屏mediaid列表，可以通过提审素材上传接口获得
     */
    @JsonProperty("pic_id_list")
    private List<String> picIdList;
}
