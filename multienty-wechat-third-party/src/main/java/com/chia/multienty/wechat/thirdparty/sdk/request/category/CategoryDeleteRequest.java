package com.chia.multienty.wechat.thirdparty.sdk.request.category;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.category.CategoryDeleteResponse;
import lombok.Data;

/**
 * 删除类目
 */
@Data
@WxApi(url = MerchantApis.Category.DELETE_CATEGORY)
public class CategoryDeleteRequest extends AuthorizerBaseRequest<CategoryDeleteResponse> {
    /**
     * 一级类目 ID
     */
    private Long first;
    /**
     * 二级类目 ID
     */
    private Long second;
}
