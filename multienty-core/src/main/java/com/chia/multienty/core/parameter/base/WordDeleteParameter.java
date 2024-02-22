package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 关键词删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WordDeleteParameter",description = "关键词删除请求")
public class WordDeleteParameter {

    /**
     * 关键词编号
     */
     @ApiModelProperty(value = "关键词编号")
     @LogMetaId
     private Long id;
}
