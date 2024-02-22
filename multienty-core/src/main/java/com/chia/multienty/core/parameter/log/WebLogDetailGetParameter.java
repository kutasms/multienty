package com.chia.multienty.core.parameter.log;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * web请求记录详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "WebLogDetailGetParameter",description = "web请求记录详情获取请求")
public class WebLogDetailGetParameter {
    /**
     * 请求记录id
     */
     @ApiModelProperty(value = "请求记录id")
     private Long logId;
}
