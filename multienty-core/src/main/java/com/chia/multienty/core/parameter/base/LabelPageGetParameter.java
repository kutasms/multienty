package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.LabelDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "LabelPageGetParameter",description = "分页列表查询请求")
public class LabelPageGetParameter extends DefaultListGetParameter<LabelDTO> {

    /**
     * 标签编号
     */
     @ApiModelProperty(value = "标签编号列表")
     private List<Long> labelIds;
}
