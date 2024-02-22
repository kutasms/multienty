package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.annotation.LogMetaId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "LogoutParameter",description = "登出请求")
public class LogoutParameter {
    private String token;

    @JsonIgnore
    @LogMetaId
    private Long userId;
}
