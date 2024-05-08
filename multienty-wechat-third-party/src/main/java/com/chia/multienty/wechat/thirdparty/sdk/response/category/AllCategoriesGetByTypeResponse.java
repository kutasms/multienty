package com.chia.multienty.wechat.thirdparty.sdk.response.category;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.cateory.MppCategoryList;
import lombok.Data;

/**
 * 获取不同类型主体可设置的类目响应数据
 */
@Data
public class AllCategoriesGetByTypeResponse extends BaseResponse {
    /**
     * 类目信息列表
     */
    private MppCategoryList categoriesList;
}
