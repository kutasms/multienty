package com.chia.multienty.core.parameter.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CityGetParameter",description = "城市获取请求")
public class CityGetParameter {
    @ApiModelProperty("省份名")
    private String provinceName;
    @ApiModelProperty("城市名")
    private String cityName;
    @ApiModelProperty("区县名")
    private String districtName;
}
