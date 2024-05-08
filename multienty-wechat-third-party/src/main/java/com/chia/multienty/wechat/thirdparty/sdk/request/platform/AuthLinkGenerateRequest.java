package com.chia.multienty.wechat.thirdparty.sdk.request.platform;

import lombok.Data;

@Data
public class AuthLinkGenerateRequest {

    /**
     * 	- 授权回调 URI(填写格式为https://xxx)。（插件版无该参数）<br/>
     * - 管理员授权确认之后会自动跳转进入回调 URI，<br/>
     * 并在 URL 参数中返回授权码和过期时间(redirect_url?auth_code=xxx&expires_in=600)<br/>
     */
    private String redirectUrl;

    /**
     * 要授权的账号类型，即商家点击授权链接或者扫了授权码之后，展示在用户手机端的授权账号类型。<br/>
     * - 1 表示手机端仅展示公众号；2 表示仅展示小程序，3 表示公众号和小程序都展示。<br/>
     * - 4表示小程序推客账号；<br/>
     * - 5表示视频号账号；<br/>
     * - 6表示全部，即公众号、小程序、视频号都展示<br/>
     * - 第三方平台开发者可以使用本字段来控制授权的账号类型。<br/>
     * - 对于已经注销、冻结、封禁、以及未完成注册的账号不再出现于授权账号列表。
     */
    private Integer authType;

}
