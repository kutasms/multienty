package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.annotation.WxFormData;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.TempAssetAddResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 新增临时素材
 */
@Data
@WxApi(url = MerchantApis.OfficialAccount.ADD_TEMP_ASSET, postForm = true)
public class TempAssetAddRequest extends AuthorizerBaseRequest<TempAssetAddResponse> {
    private String type;
    @WxFormData
    @JsonIgnore
    private MultipartFile file;
}
