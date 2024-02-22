package com.chia.multienty.core.parameter.base;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 通知禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "NoticeDisableParameter",description = "通知禁用请求")
public class NoticeDisableParameter {
    /**
     * 通知编号
     */
     @ApiModelProperty(value = "通知编号")
     private Long noticeId;
}
