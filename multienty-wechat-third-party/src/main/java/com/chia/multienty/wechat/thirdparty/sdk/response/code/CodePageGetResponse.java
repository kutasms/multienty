package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 * 获取已上传的代码页面列表响应数据
 */
@Data
public class CodePageGetResponse extends BaseResponse {
    /**
     * page_list 页面配置列表
     */
    private List<String> pageList;
}
