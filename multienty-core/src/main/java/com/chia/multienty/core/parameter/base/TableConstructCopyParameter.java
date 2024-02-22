package com.chia.multienty.core.parameter.base;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 表格结构复制请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
@Data
@ApiModel(value = "TableConstructCopyParameter",description = "表格结构复制请求")
public class TableConstructCopyParameter {
    private String sourceTableName;
    private String targetTableName;

    private Long autoIncrement =  1L;
}
