package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户密钥更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "TenantSecretUpdateParameter",description = "租户密钥更新请求")
@Accessors(chain = true)
public class TenantSecretUpdateParameter {

    /**
     * 密钥编号
     */
     @ApiModelProperty(value = "密钥编号")
     @LogMetaId
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
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 是否已删除
     */
     @ApiModelProperty(value = "是否已删除")
     private Boolean deleted;
}
