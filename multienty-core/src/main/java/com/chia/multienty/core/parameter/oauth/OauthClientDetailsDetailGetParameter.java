package com.chia.multienty.core.parameter.oauth;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 客户端信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "OauthClientDetailsDetailGetParameter",description = "客户端信息详情获取请求")
public class OauthClientDetailsDetailGetParameter {
    /**
     * 客户端ID
     */
     @ApiModelProperty(value = "客户端ID")
     private String clientId;
}
