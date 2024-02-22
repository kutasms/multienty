package com.chia.multienty.core.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * */
@Data
@Accessors(chain = true)
@Builder
public class PublicKeyDTO {
    @ApiModelProperty("是否模拟服务")
    private Boolean mockServer;
    @ApiModelProperty("公钥")
    private String publicKey;
}
