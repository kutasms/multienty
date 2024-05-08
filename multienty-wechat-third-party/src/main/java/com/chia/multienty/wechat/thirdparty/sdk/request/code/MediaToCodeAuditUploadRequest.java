package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.annotation.WxFormData;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.MediaToCodeAuditUploadResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传提审素材
 */
@Data
@WxApi(url = MerchantApis.Code.UPLOAD_MEDIA_TO_CODE_AUDIT)
public class MediaToCodeAuditUploadRequest extends AuthorizerBaseRequest<MediaToCodeAuditUploadResponse> {
    /**
     * 图片（image）: 2M，支持PNG\JPEG\JPG\GIF格式 视频（video）：10MB，
     * 支持MP4格式 完成素材上传后，使用返回的mediaid，可以在提审接口通过post
     * preview_info完成图片和视频上传。 注意：返回的 mediaid 有效期是三天，
     * 过期需要重新上传
     */
    @WxFormData
    @JsonIgnore
    private MultipartFile file;
}
