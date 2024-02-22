package com.chia.multienty.core.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 标签-值的键值对
 * */
@Data
@ApiModel(value = "LabelValuePair",description = "标签-值的键值对")
@AllArgsConstructor
public class LabelValuePair {
    private String label;
    private Object value;
    /**
     * 是否叶子节点
     * */
    private Boolean leaf;
}
