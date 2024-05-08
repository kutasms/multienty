package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 中国城市
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_chinese_city")
@ApiModel(value="ChineseCity对象", description="中国城市")
public class ChineseCity extends KutaBaseEntity {


    /**
     * 城市编号
     */
    @ApiModelProperty(value = "城市编号")
    @TableId(value = "city_id", type = IdType.INPUT)
    private Long cityId;

    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级编号")
    @TableField("`city_pid`")
    private Long cityPid;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    @TableField("`city_name`")
    private String cityName;

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


}
