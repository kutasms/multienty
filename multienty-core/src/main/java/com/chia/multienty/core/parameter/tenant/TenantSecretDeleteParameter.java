package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import javax.validation.constraints.NotNull;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户密钥删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "TenantSecretDeleteParameter",description = "租户密钥删除请求")
@Accessors(chain = true)
public class TenantSecretDeleteParameter {

    /**
     * 密钥编号
     */
    @ApiModelProperty(value = "密钥编号")
    @LogMetaId
    private Long secretId;

}
