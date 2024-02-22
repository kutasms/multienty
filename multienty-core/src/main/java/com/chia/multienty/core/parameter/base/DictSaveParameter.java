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
 * 数据字典信息保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "DictSaveParameter",description = "数据字典信息保存请求")
public class DictSaveParameter {

    /**
     * 字典id
     */
    @ApiModelProperty(value = "字典id")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long dictId;
    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id")
    private Long pid;
    /**
     * 字典标签
     */
    @ApiModelProperty(value = "字典标签")
    private String label;
    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    private String alias;
    /**
     * 字典值
     */
    @ApiModelProperty(value = "字典值")
    private String value;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 值类型
     */
    @ApiModelProperty(value = "值类型")
    private String valueType;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
    /**
     * 是否可删除(在创建时设置)
     */
    @ApiModelProperty(value = "是否可删除(在创建时设置)")
    private Boolean deletable;
    /**
     * 是否已加密
     */
    @ApiModelProperty(value = "是否已加密")
    private Boolean encrypted;
}
