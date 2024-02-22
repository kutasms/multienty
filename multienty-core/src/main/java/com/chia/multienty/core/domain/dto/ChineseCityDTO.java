package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.ChineseCity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 城市管理信息表 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="ChineseCityDTO", description="城市管理信息表DTO对象")
public class ChineseCityDTO extends ChineseCity {

    @ApiModelProperty("省份")
    private ChineseCity province;
    @ApiModelProperty("城市")
    private ChineseCity city;
    @ApiModelProperty("区县")
    private ChineseCity district;
}
