package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.CalendarDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;

import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 用于链表的连续日期信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "CalendarPageGetParameter",description = "用于链表的连续日期信息分页列表查询请求")
public class CalendarPageGetParameter extends DefaultListGetParameter<CalendarDTO> {

    /**
     * 日期
     */
     @ApiModelProperty(value = "日期列表")
     private List<LocalDate> days;
}
