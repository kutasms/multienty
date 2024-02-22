package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.DictDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 数据字典信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "DictPageGetParameter",description = "数据字典信息分页列表查询请求")
public class DictPageGetParameter extends DefaultListGetParameter<DictDTO> {

    /**
     * 字典id
     */
     @ApiModelProperty(value = "字典id列表")
     private List<Long> dictIds;
}
