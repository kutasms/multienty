package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.JsonConfig;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * Json格式配置信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="JsonConfigDTO", description="Json格式配置信息DTO对象")
public class JsonConfigDTO extends JsonConfig {
}
