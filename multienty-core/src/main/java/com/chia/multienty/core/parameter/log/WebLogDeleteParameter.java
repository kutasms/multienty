package com.chia.multienty.core.parameter.log;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * web请求记录删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WebLogDeleteParameter",description = "web请求记录删除请求")
public class WebLogDeleteParameter {

    /**
     * 请求记录id
     */
     @ApiModelProperty(value = "请求记录id")
     @LogMetaId
     private Long logId;
}
