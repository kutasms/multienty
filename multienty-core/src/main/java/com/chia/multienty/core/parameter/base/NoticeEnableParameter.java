package com.chia.multienty.core.parameter.base;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 通知启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "NoticeEnableParameter",description = "通知启用请求")
public class NoticeEnableParameter {
    /**
     * 通知编号
     */
     @ApiModelProperty(value = "通知编号")
     private Long noticeId;
}
