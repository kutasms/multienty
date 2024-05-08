package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.annotation.LogMetaId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "LoginParameter",description = "登录请求")
public class LoginParameter implements Serializable {
    /**
     * 经过加密的参数
     * */
    @ApiModelProperty("经过加密的参数")
    private String param;
    /**
     * 用户编号
     */
    @ApiModelProperty("用户编号")
    @JsonIgnore
    @LogMetaId
    private Long userId;

}
