package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.ChineseCityDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 城市管理信息表分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "ChineseCityPageGetParameter",description = "城市管理信息表分页列表查询请求")
public class ChineseCityPageGetParameter extends DefaultListGetParameter<ChineseCityDTO> {

    /**
     * 城市id
     */
     @ApiModelProperty(value = "城市id列表")
     private List<Long> cityIds;
    /**
     * 父级城市id
     */
    @ApiModelProperty(value = "父级城市id")
     private Long cityPid;
}
