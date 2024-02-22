package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 日历保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-16
 */

@Data
@ApiModel(value = "CalendarSaveParameter",description = "日历保存请求")
public class CalendarSaveParameter {

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private LocalDate day;
}
