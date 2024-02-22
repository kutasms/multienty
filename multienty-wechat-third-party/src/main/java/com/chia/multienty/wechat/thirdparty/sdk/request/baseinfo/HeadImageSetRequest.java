package com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.HeadImageSetResponse;
import lombok.Data;

/**
 * 修改头像
 */
@Data
@WxApi(url = MerchantApis.BaseInfo.SET_HEAD_IMAGE)
public class HeadImageSetRequest extends AuthorizerBaseRequest<HeadImageSetResponse> {
    /**
     * 头像素材 media_id
     */
    private String headImgMediaId;
    /**
     * 裁剪框左上角 x 坐标（取值范围：[0, 1]）
     */
    private String x1;
    /**
     * 裁剪框左上角 y 坐标（取值范围：[0, 1]）
     */
    private String y1;
    /**
     * 裁剪框右下角 x 坐标（取值范围：[0, 1]）
     */
    private String x2;
    /**
     * 裁剪框右下角 y 坐标（取值范围：[0, 1]）
     */
    private String y2;
}
