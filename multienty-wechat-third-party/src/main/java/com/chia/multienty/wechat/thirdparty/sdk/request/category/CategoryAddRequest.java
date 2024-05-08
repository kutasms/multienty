package com.chia.multienty.wechat.thirdparty.sdk.request.category;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.cateory.MppCategoryAddParameter;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.category.CategoryAddResponse;
import lombok.Data;

import java.util.List;

/**
 * 添加类目
 */
@Data
@WxApi(url = MerchantApis.Category.ADD_CATEGORY)
public class CategoryAddRequest extends AuthorizerBaseRequest<CategoryAddResponse> {
    /**
     * 类目信息列表
     */
    private List<MppCategoryAddParameter> categories;
}
