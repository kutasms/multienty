package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.SecretAuth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 密钥授权 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SecretAuthDTO", description="密钥授权DTO对象")
public class SecretAuthDTO extends SecretAuth {
}
