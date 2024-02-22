package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.Dict;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 数据字典信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="DictDTO", description="数据字典信息DTO对象")
public class DictDTO extends Dict {
}
