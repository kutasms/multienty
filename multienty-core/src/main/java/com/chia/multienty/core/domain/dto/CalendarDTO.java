package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.Calendar;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 用于链表的连续日期信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CalendarDTO", description="用于链表的连续日期信息DTO对象")
public class CalendarDTO extends Calendar {
}
