package com.chia.multienty.wechat.thirdparty.sdk.response.oa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 新增临时素材响应数据
 */
@Data
public class TempAssetAddResponse extends BaseResponse {
    /**
     * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     */
    private String type;
    /**
     * 媒体文件上传后，获取标识
     */
    private String mediaId;
    /**
     * 媒体文件上传时间戳
     */
    private Long createdAt;
}
