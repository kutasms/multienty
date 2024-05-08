package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 应用详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "AppDetailGetParameter",description = "应用详情获取请求")
public class AppDetailGetParameter {
    /**
     * 应用编号
     */
     @ApiModelProperty(value = "应用编号")
     private Long appId;
}
