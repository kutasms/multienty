package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户密钥保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "TenantSecretSaveParameter",description = "租户密钥保存请求")
@Accessors(chain = true)
public class TenantSecretSaveParameter {

    /**
     * 密钥编号
     */
    @ApiModelProperty(value = "密钥编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long secretId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    private Long tenantId;
    /**
     * 子账号编号
     */
    @ApiModelProperty(value = "子账号编号")
    private Long subAccId;
    /**
     * 应用key
     */
    @ApiModelProperty(value = "应用key")
    private String appKey;
    /**
     * 应用密钥
     */
    @ApiModelProperty(value = "应用密钥")
    private String appSecret;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
