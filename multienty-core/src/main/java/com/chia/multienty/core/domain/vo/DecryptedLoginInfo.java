package com.chia.multienty.core.domain.vo;

import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.LoginMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "DecryptedLoginInfo",description = "已解密的登录信息")
public class DecryptedLoginInfo {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("验证码")
    private String code;

    /**
     * 应用编号
     */
    @ApiModelProperty("应用编号")
    private Long appId = ApplicationType.PLATFORM.getValue();

    /**
     * 登录类型
     */
    @ApiModelProperty("登录类型")
    private LoginMode loginMode = LoginMode.USERNAME_PASSWORD;

}
