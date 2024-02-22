package com.chia.multienty.core.parameter.base;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 关键词信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "WordDetailGetParameter",description = "关键词信息详情获取请求")
public class WordDetailGetParameter {
    /**
     * 关键词id
     */
     @ApiModelProperty(value = "关键词id")
     private Long id;
}
