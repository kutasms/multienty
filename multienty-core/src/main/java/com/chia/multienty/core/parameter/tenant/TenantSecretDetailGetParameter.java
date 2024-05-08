package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户密钥详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "TenantSecretDetailGetParameter",description = "租户密钥详情获取请求")
public class TenantSecretDetailGetParameter {
    /**
     * 密钥编号
     */
     @ApiModelProperty(value = "密钥编号")
     private Long secretId;
}
