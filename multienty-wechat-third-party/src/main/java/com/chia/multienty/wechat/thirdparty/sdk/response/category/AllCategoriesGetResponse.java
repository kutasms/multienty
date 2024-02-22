package com.chia.multienty.wechat.thirdparty.sdk.response.category;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.cateory.MppCategory;
import lombok.Data;

import java.util.List;

/**
 * 获取可设置的所有类目响应数据
 */
@Data
public class AllCategoriesGetResponse extends BaseResponse {
    /**
     * 类目信息列表
     */
    private List<MppCategory> categoriesList;
}
