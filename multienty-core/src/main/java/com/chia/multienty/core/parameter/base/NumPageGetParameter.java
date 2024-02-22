package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.NumDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;

/**
 * <p>
 * 数字信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "NumPageGetParameter",description = "数字信息分页列表查询请求")
public class NumPageGetParameter extends DefaultListGetParameter<NumDTO> {

}
