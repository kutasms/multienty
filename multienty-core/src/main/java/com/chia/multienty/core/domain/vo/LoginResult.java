package com.chia.multienty.core.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel(value = "LoginResult",description = "登录结果")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private String accessToken;
    /**
     * 用户信息
     */
    private Object userInfo;

}
