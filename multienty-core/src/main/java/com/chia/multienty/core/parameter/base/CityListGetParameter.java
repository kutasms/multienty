package com.chia.multienty.core.parameter.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 城市列表获取请求
 * */
@Data
@ApiModel(value = "CityListGetParameter",description = "城市列表获取请求")
public class CityListGetParameter {
    /**
     * 父级城市编号
     * */
    @ApiModelProperty("父级城市编号")
    private Long parentId = 0L; //默认一级城市/省份
}
