package com.chia.multienty.core.parameter;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 空参数请求
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BlankParameter",description = "空参数请求")
public class BlankParameter {
}
