package com.chia.multienty.wechat.thirdparty.sdk.request.category;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.category.AllCategoriesGetByTypeResponse;
import lombok.Data;

/**
 * 获取不同类型主体可设置的类目
 */
@Data
@WxApi(url = MerchantApis.Category.GET_ALL_CATEGORIES_BY_TYPE)
public class AllCategoriesGetByTypeRequest extends AuthorizerBaseRequest<AllCategoriesGetByTypeResponse> {
    /**
     * 如果不填，默认传0；个人主体是0；企业主体是1；政府是2；媒体是3；其他组织是4
     */
    private Integer verifyType;
}
