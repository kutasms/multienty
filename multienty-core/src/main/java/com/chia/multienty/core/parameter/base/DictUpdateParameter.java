package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 数据字典信息更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "DictUpdateParameter",description = "数据字典信息更新请求")
public class DictUpdateParameter {

    /**
     * 字典id
     */
     @ApiModelProperty(value = "字典id")
     @LogMetaId
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
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 是否可删除(在创建时设置)
     */
     @ApiModelProperty(value = "是否可删除(在创建时设置)")
     private Boolean deletable;
    /**
     * 状态(NORMAL-正常)
     */
     @ApiModelProperty(value = "状态(NORMAL-正常)")
     private String status;
    /**
     * 乐观锁版本号
     */
     @ApiModelProperty(value = "乐观锁版本号")
     private Long version;
    /**
     * 是否已加密
     */
     @ApiModelProperty(value = "是否已加密")
     private Boolean encrypted;
}
