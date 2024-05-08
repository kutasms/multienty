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
 * 应用实例详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "AppInstanceDetailGetParameter",description = "应用实例详情获取请求")
public class AppInstanceDetailGetParameter {
    /**
     * 实例编号
     */
     @ApiModelProperty(value = "实例编号")
     private Long instanceId;
}
