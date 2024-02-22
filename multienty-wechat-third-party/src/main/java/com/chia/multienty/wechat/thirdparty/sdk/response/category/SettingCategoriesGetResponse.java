package com.chia.multienty.wechat.thirdparty.sdk.response.category;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.cateory.MppSettingCategory;
import lombok.Data;

import java.util.List;

/**
 * 获取已设置的所有类目响应数据
 */
@Data
public class SettingCategoriesGetResponse extends BaseResponse {
    /**
     * 已设置的类目信息列表
     */
    private List<MppSettingCategory> categories;
    /**
     * 一个更改周期内可以添加类目的次数
     */
    private Integer limit;
    /**
     * 本更改周期内还可以添加类目的次数
     */
    private Integer quota;
    /**
     * 最多可以设置的类目数量
     */
    private Integer categoryLimit;
}
