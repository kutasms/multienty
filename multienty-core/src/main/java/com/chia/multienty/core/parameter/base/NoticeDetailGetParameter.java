package com.chia.multienty.core.parameter.base;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 系统通知信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "NoticeDetailGetParameter",description = "系统通知信息详情获取请求")
public class NoticeDetailGetParameter {
    /**
     * 消息id
     */
     @ApiModelProperty(value = "消息id")
     private Long noticeId;
}
