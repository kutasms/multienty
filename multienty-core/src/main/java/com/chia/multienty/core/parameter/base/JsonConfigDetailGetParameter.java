package com.chia.multienty.core.parameter.base;

import io.swagger.annotations.ApiModel;
import lombok.Data;
/**
 * <p>
 * Json格式配置信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "JsonConfigDetailGetParameter",description = "Json格式配置信息详情获取请求")
public class JsonConfigDetailGetParameter {

     private Long configId;
}
