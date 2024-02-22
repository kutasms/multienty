package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.basic.PagedParameter;
import com.chia.multienty.core.pojo.Label;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 标签推荐请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "LabelRecommendParameter",description = "标签推荐请求")
public class LabelRecommendParameter extends PagedParameter<Label> {
    private String inputKeywords;
}
