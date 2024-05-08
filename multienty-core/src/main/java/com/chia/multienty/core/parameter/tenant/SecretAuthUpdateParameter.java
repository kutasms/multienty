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
 * 密钥授权更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "SecretAuthUpdateParameter",description = "密钥授权更新请求")
@Accessors(chain = true)
public class SecretAuthUpdateParameter {

    /**
     * 密钥授权编号
     */
     @ApiModelProperty(value = "密钥授权编号")
     @LogMetaId
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
