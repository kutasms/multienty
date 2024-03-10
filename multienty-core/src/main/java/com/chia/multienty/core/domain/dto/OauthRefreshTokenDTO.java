package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.OauthRefreshToken;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 更新令牌 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="OauthRefreshTokenDTO", description="更新令牌DTO对象")
public class OauthRefreshTokenDTO extends OauthRefreshToken {
}
