package com.chia.multienty.wechat.thirdparty.sdk.response.register;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 注册试用小程序
 */
@Data
public class BetaMiniProgramRegisterResponse extends BaseResponse {
    /**
     * 该请求的唯一标识符，用于关联微信用户和后面产生的appid
     */
    private String uniqueId;
    /**
     * 用户授权确认url，需将该url发送给用户，小程序管理员在微信打开并进入授权页面完成授权方可创建小程序
     */
    private String authorizeUrl;
}
