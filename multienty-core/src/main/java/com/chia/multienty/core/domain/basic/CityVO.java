package com.chia.multienty.core.domain.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "CityVO",description = "城市VO信息")
public class CityVO {
    @ApiModelProperty("最终构造的城市名称")
    private String label;
    @ApiModelProperty("选中的省市区ID集合")
    private String[] ids;
    @ApiModelProperty("选中的城市编号")
    private Long cityId;

}
