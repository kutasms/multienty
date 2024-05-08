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
 * 密钥授权保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "SecretAuthSaveParameter",description = "密钥授权保存请求")
@Accessors(chain = true)
public class SecretAuthSaveParameter {

    /**
     * 密钥授权编号
     */
    @ApiModelProperty(value = "密钥授权编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long secAuthId;
    /**
     * 密钥编号
     */
    @ApiModelProperty(value = "密钥编号")
    private Long secretId;
    /**
     * 实例编号
     */
    @ApiModelProperty(value = "实例编号")
    private Long instanceId;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
