package com.chia.multienty.core.parameter.base;

import lombok.Data;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 用于链表的连续日期信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "CalendarDetailGetParameter",description = "用于链表的连续日期信息详情获取请求")
public class CalendarDetailGetParameter {
    /**
     * 日期
     */
     @ApiModelProperty(value = "日期")
     private LocalDate day;
}
