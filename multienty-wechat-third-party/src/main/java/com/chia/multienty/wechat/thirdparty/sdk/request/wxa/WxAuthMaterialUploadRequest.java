package com.chia.multienty.wechat.thirdparty.sdk.request.wxa;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.annotation.WxFormData;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthMaterialUploadResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 小程序认证上传补充材料
 */
@Data
@WxApi(url = MerchantApis.WxAuth.UPLOAD_AUTH_MATERIAL)
public class WxAuthMaterialUploadRequest extends AuthorizerBaseRequest<WxAuthMaterialUploadResponse> {
    /**
     * 图片（image）: 不超过2M，支持PNG\JPEG\JPG\GIF格式
     */
    @WxFormData
    @JsonIgnore
    private MultipartFile file;
}
