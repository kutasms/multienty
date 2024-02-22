package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;

import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 日历删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "CalendarDeleteParameter",description = "日历删除请求")
public class CalendarDeleteParameter {

    /**
     * 日期
     */
     @ApiModelProperty(value = "日期")
     @LogMetaId
     private LocalDate day;
}
