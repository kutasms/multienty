package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.code.CodeVersion;
import lombok.Data;

/**
 * 小程序版本回退响应数据
 */
@Data
public class CodeReleaseRevertResponse extends BaseResponse {
    /**
     * 模板信息列表。当action=get_history_version，才会返回
     */
    private CodeVersion versionList;
}
