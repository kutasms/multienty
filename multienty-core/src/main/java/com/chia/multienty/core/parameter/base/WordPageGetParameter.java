package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.WordDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 关键词信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "WordPageGetParameter",description = "关键词信息分页列表查询请求")
public class WordPageGetParameter extends DefaultListGetParameter<WordDTO> {

    /**
     * 关键词id
     */
     @ApiModelProperty(value = "关键词id列表")
     private List<Long> ids;
}
