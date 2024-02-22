package com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

@Data
public class BindOpenAccountGetResponse extends BaseResponse {
    /**
     * 是否绑定open账号，true表示绑定；false表示未绑定任何open账号
     */
    private Boolean haveOpen;
}
