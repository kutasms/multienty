package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.JsonConfigDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;

import java.util.List;
/**
 * <p>
 * Json格式配置信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "JsonConfigPageGetParameter",description = "Json格式配置信息分页列表查询请求")
public class JsonConfigPageGetParameter extends DefaultListGetParameter<JsonConfigDTO> {

     private List<Long> configIds;
}
