package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.Num;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 数字信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="NumDTO", description="数字信息DTO对象")
public class NumDTO extends Num {
}
