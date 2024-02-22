package com.chia.multienty.core.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel(value = "LoginResult",description = "登录结果")
@Builder
public class LoginResult {
    @JsonProperty(value = "")
    private String accessToken;
}
