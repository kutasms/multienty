package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.DictDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "PlatformDictListGetParameter",description = "平台字典列表获取请求")
public class DictListGetParameter extends DefaultListGetParameter<DictDTO> {
    @ApiModelProperty("父级编号")
    private Long pid = 0L;
}
