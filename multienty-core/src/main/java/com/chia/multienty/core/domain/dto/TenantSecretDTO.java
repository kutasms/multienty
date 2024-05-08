package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.TenantSecret;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 租户密钥 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TenantSecretDTO", description="租户密钥DTO对象")
public class TenantSecretDTO extends TenantSecret {
}
