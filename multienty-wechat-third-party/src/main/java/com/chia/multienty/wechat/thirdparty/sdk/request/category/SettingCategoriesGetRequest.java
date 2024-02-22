package com.chia.multienty.wechat.thirdparty.sdk.request.category;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.IWxRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.category.SettingCategoriesGetResponse;
import lombok.Data;

/**
 * 获取已设置的所有类目
 */
@Data
@WxApi(url = MerchantApis.Category.GET_SETTING_CATEGORIES, method = WxApi.Method.GET)
public class SettingCategoriesGetRequest implements IWxRequest<SettingCategoriesGetResponse> {

}
