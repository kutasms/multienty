package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 关键词信息保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */

@Data
@ApiModel(value = "WordSaveParameter",description = "关键词信息保存请求")
public class WordSaveParameter {

    /**
     * 关键词id
     */
    @ApiModelProperty(value = "关键词id")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long id;
    /**
     * 关键词内容
     */
    @ApiModelProperty(value = "关键词内容")
    private String keyword;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    /**
     * 类型  FORBIDDEN - 禁止的 ALLOWED - 允许的
     */
    @ApiModelProperty(value = "类型  FORBIDDEN - 禁止的 ALLOWED - 允许的")
    private String type;
}
