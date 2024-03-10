package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.OauthAccessToken;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 访问令牌 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="OauthAccessTokenDTO", description="访问令牌DTO对象")
public class OauthAccessTokenDTO extends OauthAccessToken {
}
