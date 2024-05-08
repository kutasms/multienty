package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 插件价格策略
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_plugin_price_strategy")
@ApiModel(value="PluginPriceStrategy对象", description="插件价格策略")
public class PluginPriceStrategy extends KutaBaseEntity {


    /**
     * 插件价格策略编号
     */
    @ApiModelProperty(value = "插件价格策略编号")
    @TableId(value = "strategy_id", type = IdType.AUTO)
    private Long strategyId;

    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    @TableField("`plugin_id`")
    private Long pluginId;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    @TableField("`price`")
    private BigDecimal price;

    /**
     * 作用域
     */
    @ApiModelProperty(value = "作用域")
    @TableField("`scope`")
    private String scope;

    /**
     * 是否免费
     */
    @ApiModelProperty(value = "是否免费")
    @TableField("`is_free`")
    private Boolean isFree;

    /**
     * 可试用
     */
    @ApiModelProperty(value = "可试用")
    @TableField("`triable`")
    private Boolean triable;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "`update_time`", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 乐观锁版本号
     */
    @ApiModelProperty(value = "乐观锁版本号")
    @TableField(value = "`version`", fill = FieldFill.INSERT)
    private Long version;


}
